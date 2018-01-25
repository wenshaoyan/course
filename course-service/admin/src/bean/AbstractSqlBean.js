const { Where, AbstractSql } = require('../gen-nodejs/bean_types');
class AbstractSqlBean extends AbstractSql {
	constructor(args) {
		if (args && args.where) {
			for (const key in args.where) {
				args.where[key] = new Where(args.where[key]);
			}
		}
		super(args);
	}
}
module.exports = AbstractSqlBean;