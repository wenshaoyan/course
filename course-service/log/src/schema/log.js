/**
 * Created by yanshaowen on 2018/01/03
 */
const mongoose = require('../utils/mongodb-util'),
	Schema = mongoose.Schema;

const LogSchema = new Schema({
	a: {type: String},
	t: {type: Date},
	p: {type: String},
	g: {type: String},
	m: {type: String},
});

function dynamicModel(serverName, date) {
	return mongoose.model(serverName + '_Log_' + date, LogSchema);
}

module.exports = {dynamicModel};
