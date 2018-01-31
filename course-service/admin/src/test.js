
class A {
	constructor(){

	}
}
const a = new A();
const b = Object.getPrototypeOf(a);
console.log(typeof b.constructor.name)