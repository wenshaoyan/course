/**
 * mongodb 连接
 */
const mongoose = require('mongoose');
const serviceConfig = getServiceConfig();

/**
 * 连接
 */
mongoose.connect(serviceConfig.mongodbServer, serviceConfig.mongodbOptions);
/**
 * 连接成功
 */
mongoose.connection.on('connected', function () {
	// console.log('Mongoose connection open to ' + serviceConfig.mongodbServer);
});

/**
 * 连接异常
 */
mongoose.connection.on('error', function (err) {
	console.log('Mongoose connection error: ' + err);
});

/**
 * 连接断开
 */
mongoose.connection.on('disconnected', function () {
	console.log('Mongoose connection disconnected');
});

module.exports = mongoose;