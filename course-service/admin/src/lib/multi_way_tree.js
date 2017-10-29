/**
 * Created by wenshao on 2017/8/19.
 * 多叉树的实现
 */
'use strict';

const MultiWayTree = (function () {
    /**
     * 节点
     */
    class Node {
        constructor(key, parent, value) {
            this._key = key;        // 节点唯一标识
            this._childes = [];     // 所有子节点节点集合
            this._value = value;    // 节点内容
            this._parents = new Set(); // 父节点 可能有多个
            this._parents.add(parent);
        }

        get key() {
            return this._key;
        }

        set key(value) {
            this._key = value;
        }


        get childes() {
            return this._childes;
        }

        set childes(value) {
            this._childes = value;
        }

        get value() {
            return this._value;
        }

        set value(value) {
            this._value = value;
        }

        get parents() {
            return this._parents;
        }

        set parents(value) {
            this._parents = value;
        }
    }

    // 根节点
    const _root = ('_root');
    // 根节点对应的标识符号
    const _root_tag = ('_root_tag');
    // 插入节点函数
    const _func_insert_node = ('_func_insert_node');
    // 递归树函数
    const _func_recursion_node = ('_func_recursion_node');

    const _wait_allot = ('_wait_allot');
    // 待分配节点队列
    const _wait_allot_set = ('_wait_allot_set');
    // 根据code对应node的映射
    const _node_hash = ('_node_hash');
    //
    const _childes_wait = ('_childes_wait');

    const insertNode = function (root, node) {
        let result;
        if (node.parents.has(root.key)) {
            root.childes.push(node);
            return true;
        }
        for (let val of root.childes) {
            let insertNodeResult = insertNode(val, node);
            if (insertNodeResult) {
                result = true;
                break;
            }

        }
        return result;

    };
    const recursion = function (list, chiles, callback) {
        let index = 0;
        for (let val of chiles) {
            let parse = val.value;
            if (callback instanceof Function) callback(parse);
            list.push(parse);
            recursion(list[index].next, val.childes, callback);
            index++;
        }

    };

    class MultiWayTree {
        constructor(keyName) {
            this[_root] = new Node(_root_tag, null, {next: []});
            this._keyName = keyName;
            this[_func_insert_node] = insertNode;
            this[_func_recursion_node] = recursion;
            this[_wait_allot] = new Map();
            this[_wait_allot_set] = new Set();
            this[_childes_wait] = new Map();
            this[_node_hash] = new Map();
        }

        get keyName() {
            return this._keyName;
        }

        set keyName(value) {
            this._keyName = value;
        }

        insert(value) {

            if (!(this._keyName in value) || !('parent' in value)) {
                return false;
            }
            if (this[_node_hash].has(value[this.keyName])) {
                const getNode = this[_node_hash].get(value[this.keyName]);
                if (getNode.parents.has(value.parent)) {   // 重复数据 不添加
                    console.error(`${this.keyName}=${value[this.keyName]} and parent=${value.parent} is repeat`);
                } else {
                    getNode.parents.add(value.parent);
                    // 多个父节点时  往value[this.keyName]对应的父节点的childes添加节点
                    if (this[_node_hash].has(value.parent)) {
                        this[_node_hash].get(value.parent).childes.push(getNode);
                    } else {
                        this[_childes_wait].set(value.parent, getNode);
                    }
                }
                return true;
            }
            let valueCopy = JSON.parse(JSON.stringify(value));
            valueCopy.next = [];
            const node = new Node(value[this.keyName], value.parent, valueCopy);
            if (this[_childes_wait].has(node.key)) {
                node.childes.push(this[_childes_wait].get(node.key));
                this[_childes_wait].delete(node.key);
            }
            if (!valueCopy.parent) {    // 一级
                node.parents.add(this[_root]);
                this[_root].childes.push(node);
                this[_node_hash].set(node.key, node);

            } else {
                const newVar = this[_func_insert_node](this[_root], node);
                if (!newVar) {  // 添加到待分配中
                    this[_wait_allot_set].add(node);
                } else {
                    this[_node_hash].set(node.key, node);

                }
            }
            // 每次需要把待分配队列递归遍历
            this.clearWait(node);
            return true;
        }

        clearWait(node) {
            let isRecursion = false;
            let nodeList = [];
            for (let values of this[_wait_allot_set]) {
                if (values.parents.has(node.key)) {
                    const newVar = this[_func_insert_node](node, values);
                    if (newVar) this[_node_hash].set(node.key, node);
                    this[_wait_allot_set].delete(values);
                    isRecursion = true;
                    nodeList.push(values);
                }
            }
            if (isRecursion && this[_wait_allot_set].size !== 0) {
                for (let val of nodeList) {
                    this.clearWait(val);
                }
            }
        }

        /**
         * 格式化输出
         * @param callback      定义函数 可自由控制输出的value值
         * @return {Array}
         */
        toArray(callback) {
            const list = [];
            this[_func_recursion_node](list, this[_root].childes, callback);
            console.log(this[_wait_allot_set].size)
            if (this[_wait_allot_set].size !== 0) {
                this[_wait_allot_set].forEach((ele)=>{
                    let parse = JSON.parse(JSON.stringify(ele.value));
                    if (callback instanceof Function) callback(parse);
                    list.push(parse);
                })
            }
            return list;
        }


    }

    return MultiWayTree;
})();
module.exports = MultiWayTree;