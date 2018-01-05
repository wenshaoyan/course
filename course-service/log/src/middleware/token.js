/**
 * Created by wenshao on 2017/3/11.
 * 生产token id
 */

'use strict';
/**
 * Module exports.
 * @public
 */
const ioredis = require("ioredis");
const crypto = require('crypto');
let tokenCls = null;
let redis = null;

const keyToken = "token:";


module.exports = token;


function token(ops) {
    tokenCls = new Token();

    if (ops === undefined || typeof ops !== "object") {
        throw "ops error";
    }
    if ("maxAge" in ops && typeof ops["maxAge"] === "number") {
        tokenCls.maxAge = ops["maxAge"];
        tokenCls.time = new Date().getTime() + ops["maxAge"];
    }
    if ("resave" in ops && ops["resave"] === false) {
        tokenCls.re_save = false;
    }
    if (redis === null && "redisStore" in ops && typeof ops["redisStore"] === "object") {
        redis = new ioredis(ops["redisStore"]);
    }
    return async(ctx, next) => {

        let tokenID = getHttpTokenID(ctx);


        let tokenNew = new Token();
        tokenNew.maxAge = tokenCls.maxAge;
        tokenNew.tokenID = tokenID;
        tokenNew.time = new Date().getTime() + tokenNew.maxAge;

        if (tokenID === null) {  //不存在token
            createToken(ctx, tokenNew);

        } else { //存在token
            let pipeline = redis.pipeline();
            pipeline.get(keyToken + tokenID);
            await pipeline.exec((err, result) => {
                if (!err && result[0].length > 1 && result[0][1] !== null) {  //redis中存在key
                    let parse;
                    try {
                        parse = JSON.parse(result[0][1]);
                        tokenNew.cookie = parse.cookie;
                        if (tokenCls.re_save) tokenNew.time = new Date().getTime() + parse.maxAge;
                    } catch (e) {
                        throw e;
                    } finally {
                        //
                        //ctx.response.set("tokenID", tokenNew.tokenID);
                        ctx.response.append("token", `tokenId=${tokenNew.tokenID}&time=${tokenNew.time}`);

                        ctx.Token = tokenNew;
                        ctx.ws_cookie = "cookie" in parse? parse.cookie:null;
                    }

                } else {  // 不存在key则创建
                    createToken(ctx, tokenNew);
                }
            })

        }
        await next();

        // 更新redis的值
        let pipeline = redis.pipeline();
        pipeline.set(keyToken + tokenNew.tokenID, tokenNew.toJsonString());
        //noinspection JSUnresolvedFunction
        pipeline.pexpire(keyToken + tokenNew.tokenID, tokenNew.maxAge);
        pipeline.exec();

    };
}

function createToken(ctx, tokenNew) {
    tokenNew.tokenID = getTokenID();
    //ctx.response.set("tokenID", tokenNew.tokenID);
    ctx.response.append("token", `tokenId=${tokenNew.tokenID}&time=${tokenNew.time}`);
    ctx.Token = tokenNew;

}


class Token {
    //noinspection JSDuplicatedDeclaration
    constructor() {
        this._tokenID = "";
        this._maxAge = 1000 * 60 * 60 * 24;
        this._time = new Date().getTime() + this._maxAge;
        this._resave = true;
        this._cookie = {};
    }

    get tokenID() {
        return this._tokenID;
    }

    set tokenID(_tokenID) {
        this._tokenID = _tokenID;
    }

    set maxAge(_maxAge) {
        this._maxAge = _maxAge;
    }

    get maxAge() {
        return this._maxAge;
    }

    get time() {
        return this._time;
    }

    set time(_time) {
        this._time = _time;
    }

    set re_save(_resave) {
        this._resave = _resave;
    }

    get re_save() {
        return this._resave;
    }

    set cookie(_cookie) {
        this._cookie = _cookie;
    }

    get cookie() {
        return this._cookie;
    }

    toJsonString() {
        return JSON.stringify({
            maxAge: this.maxAge,
            time: this.time,
            cookie: this.cookie
        });
    }

    //static tokenID;


}


//获取请求中的tokenID
function getHttpTokenID(ctx) {
    let tokenID = null;
    let header = ctx.header;
    if (header !== null && "token" in header) {
        tokenID = header.token;
    } else if (ctx.method === "GET") {
        tokenID = ctx.query["token"];
    } else if (ctx.method === "POST") {
        tokenID = ctx.request.body["token"];
    }
    return tokenID;
}

//产生TokenID
function getTokenID() {
    const secret = 'token';
    return crypto.createHmac('sha256', secret)
        .update(new Date().getTime() + " " + Math.ceil(Math.random() * 100))
        .digest('hex');
}