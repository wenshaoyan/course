/**
 * Created by wenshao on 2018/1/30.
 * 拦截器
 */
'use strict';

class Interceptor {
	/**
	 * 找到对应类的可监听的原型和静态方法
	 * @param _clazz    监听的类
	 */
	constructor(_clazz) {
		if (!(_clazz instanceof Function)) throw new Error('clazz type not is Function');
		const _prototypes = Object.getOwnPropertyNames(_clazz.prototype);
		const prototypes = [];
		for (const p of _prototypes) {
			if (p !== 'constructor') {
				prototypes.push(p);
			}
		}
		const _statics = Object.getOwnPropertyNames(_clazz);
		const statics = [];
		for (const p of _statics) {
			if (p !== 'length' && p !== 'prototype' && p !== 'name') {
				statics.push(p);
			}
		}
		this._clazz = _clazz;
		this._prototypes = new Set(prototypes);
		this._statics = new Set(statics);

		Function.prototype.monitor = function (functionName, before, after) {
			const self = this;
			return async function () {
				if (before instanceof Function) before.call(this, {name: functionName, args: arguments});
				self.apply(this, arguments);
				if (after instanceof Function) after.call(this, {name: functionName, args: arguments})
			};
		};
	}


	get prototypes() {
		return this._prototypes;
	}

	set prototypes(value) {
		this._prototypes = value;
	}

	get statics() {
		return this._statics;
	}

	set statics(value) {
		this._statics = value;
	}

	get clazz() {
		return this._clazz;
	}

	set clazz(value) {
		this._clazz = value;
	}

	/**
	 * 按原型的name进行监听
	 * @param name
	 * @param _before
	 * @param _after
	 * @return {boolean}
	 */
	monitorPrototypeName(name, _before, _after) {
		if (this.prototypes.has(name)) {
			this.clazz.prototype[name] = this.clazz.prototype[name].monitor(name, _before, _after);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 按正则表示匹配对应的方法名
	 * @param re
	 * @param _before
	 * @param _after
	 * @return {boolean}
	 */
	monitorPrototypeRe(re, _before, _after) {

		if (typeof re.test !== 'function') {
			return false;
		}
		this.prototypes.forEach(value => {
			if (re.test(value)) this.clazz.prototype[value] = this.clazz.prototype[value].monitor(value, _before, _after);
		});
		return true;
	}

	/**
	 * 匹配所有的原型方法
	 * @param _before
	 * @param _after
	 * @return {boolean}
	 */
	monitorPrototypeAll(_before, _after) {
		this.prototypes.forEach(value => this.clazz.prototype[value] = this.clazz.prototype[value].monitor(value, _before, _after));
		return true;
	}

	monitorStaticName(name, _before, _after) {
		if (this.statics.has(name)) {
			this.clazz[name] = this.clazz[name].monitor(name, _before, _after);
			return true;
		} else {
			return false;
		}
	}

	monitorStaticRe(re, _before, _after) {
		if (re.test instanceof Function) {
			return false;
		}
		this.statics.forEach(value => {
			if (re.test(value)) this.clazz[value] = this.clazz[value].monitor(value, _before, _after);
		});
		return true;
	}

	monitorStaticAll(_before, _after) {
		this.statics.forEach(value => this.clazz[value] = this.clazz[value].monitor(value, _before, _after));
		return true;
	}

	monitorAll(_before, _after) {
		this.monitorStaticAll(_before, _after);
		this.monitorPrototypeAll(_before, _after);
		return true;
	}

	release() {
		Function.prototype.monitor = undefined;
	}


}

module.exports = Interceptor;
