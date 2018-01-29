Function.prototype.monitor = function(before, after) {
    const self = this;
    return function() {
        before.apply(this, arguments);
        self.apply(this, arguments);
        after.apply(this, arguments)
    };
};

class A {
	constructor() {

	}
	push(a,b,c) {
		console.log('==========', a,b,c)
	}
}

const a1 = new A();
const a2 = a1.push.monitor(function () {
    console.log('before')
},function () {
    console.log('after')
});


a2('1211',2,3)

// a1.push();

// console.log(A.push)


