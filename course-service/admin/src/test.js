/**
 * Created by yanshaowen on 2017/10/27
 */
'use strict';
var genericPool = require('generic-pool');
var DbDriver = require('mysql');

/**
 * Step 1 - Create pool using a factory object
 */
const factory = {
    create: function(){
        return new Promise(function(resolve, reject){
            var client = DbDriver.createConnection({
                host: '123.207.55.204',
                user: 'root',
                password: 'wen19930314106',
                database:'wenshao'});
            client.connect(function(err) {
                if (err) {
                    console.log('Database connection error');
                } else {
                    resolve(client);
                    console.log('Database connection successful');
                }
            })
        })
    },
    destroy: function(client){
        return new Promise(function(resolve){
            client.on('end', function(){
                resolve()
            })
            client.disconnect()
        })
    }
}

var opts = {
    max: 10, // maximum size of the pool
    min: 2 // minimum size of the pool
}

var myPool = genericPool.createPool(factory, opts);

/**
 * Step 2 - Use pool in your code to acquire/release resources
 */

// acquire connection - Promise is resolved
// once a resource becomes available
var resourcePromise = myPool.acquire();

resourcePromise.then(function(client) {
    console.log('in ');
    client.query("select * from city", [], function(err,result) {
        console.log(err);
        console.log(result);
        // return object back to pool
        myPool.release(client);

    });
})
    .catch(function(err){
        // handle error - this is generally a timeout or maxWaitingClients
        // error
    });

/**
 * Step 3 - Drain pool during shutdown (optional)
 */
// Only call this once in your application -- at the point you want
// to shutdown and stop using this pool.
myPool.drain(function() {
    myPool.clear();
});

