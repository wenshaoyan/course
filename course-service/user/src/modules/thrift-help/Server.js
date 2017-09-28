/**
 * Created by wenshao on 2017/9/12.
 */
'use strict';
const thrift = require('thrift');
const transport = thrift.TFramedTransport;
const protocol = thrift.TBinaryProtocol;
const logger = getLogger('thrift');
const ConnectZk = require('./ConnectZk');
class Server {
    constructor() {
        this._host = '127.0.0.1';
        this._port = '80';
        this._serverName = null;
        this._serverObject = null;
        this._connection = null;
        this._errorCallback = null;
        this._connectionStatus = 0; // 0 待连接 1 连接成功 2 连接断开
        this._min = 1;
        this._max = 10;
        this._freeClients = [];
        this._busyClients = [];
        this._client =null;
        this._nodeName = null;
    }


    get host() {
        return this._host;
    }

    set host(value) {
        this._host = value;
    }

    get port() {
        return this._port;
    }

    set port(value) {
        this._port = value;
    }


    get serverName() {
        return this._serverName;
    }

    set serverName(value) {
        this._serverName = value;
    }

    get serverObject() {
        return this._serverObject;
    }

    set serverObject(value) {
        this._serverObject = value;
    }


    get connection() {
        return this._connection;
    }

    set connection(value) {
        this._connection = value;
    }


    get errorCallback() {
        return this._errorCallback;
    }

    set errorCallback(value) {
        this._errorCallback = value;
    }


    get connectionStatus() {
        return this._connectionStatus;
    }

    set connectionStatus(value) {
        this._connectionStatus = value;
    }


    get min() {
        return this._min;
    }

    set min(value) {
        this._min = value;
    }

    get max() {
        return this._max;
    }

    set max(value) {
        this._max = value;
    }


    get nodeName() {
        return this._nodeName;
    }

    set nodeName(value) {
        this._nodeName = value;
    }


    get connectZk() {
        return this._connectZk;
    }

    set connectZk(value) {
        this._connectZk = value;
    }
    setAddress(address) {
        if (typeof address === 'string' && address.indexOf(':') !== -1) {
            const split = address.split(':');
            this.host = split[0];
            this.port = split[1];
            if (this.connectionStatus !== 0) {
                this.connect();
            }
        } else {

            throw new Error(address + ' is error!');
        }
        return this;
    }
    getAddress(){
        return this.host+':'+this.port;
    }
    setNodeName(_nodeName){
        this.nodeName = _nodeName;
        return this;
    }
    getNodeName(){
        return this.nodeName;
    }
    setServer(_serverName, _serverObject) {
        this.serverName = _serverName;
        this.serverObject = _serverObject;

        return this;
    }

    setPoolNumber(_min, _max) {
        if (typeof _min === 'number' && _min > 0 && _min < 100) this.min = _min;
        if (typeof _max === 'number' && _max > 0 && _max < 1000 && _max > _min) this.max = _max;
        return this;
    }

    setErrorCallback(callback) {
        this.errorCallback = callback;
        return this;
    }

    async connect() {
        this.close();
        this.connection = thrift.createConnection(this.host, this.port, {
            transport: transport,
            protocol: protocol
        });
        this.connection.on('error', (err) => {
            logger.error(err);
            this.connectionStatus = 2;
            if (this.errorCallback instanceof Function) this.errorCallback(err);
        });
        /*for (let index = 0; index < this.min; index++) {
            const client = thrift.createClient(this.serverObject, this.connection);
            this._freeClients.push(client);
        }*/
        this._client = thrift.createClient(this.serverObject, this.connection);
        this.connectionStatus = 1;
        logger.info(`connect ${this.host}:${this.port} `);
        return this;
    }
    getClient(){
        return this._client;
    }
    close(){
        if (this.connection){
            this.connectionStatus = 2;
            this.connection.end();
            this.connection = null;
        }
    }

}


module.exports = Server;
