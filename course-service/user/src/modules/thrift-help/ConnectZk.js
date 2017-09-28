/**
 * Created by wenshao on 2017/9/17.
 */
'use strict';
class ConnectZk {
    constructor(_parentPath, _client) {
        this._parentPath = _parentPath;
        this._preNodeList = null;
        this._client = _client;
        this._server = null;
    }

    get parentPath() {
        return this._parentPath;
    }

    set parentPath(value) {
        this._parentPath = value;
    }

    get preNodeList() {
        return this._preNodeList;
    }

    set preNodeList(value) {
        this._preNodeList = value;
    }


    get client() {
        return this._client;
    }

    set client(value) {
        this._client = value;
    }


    get server() {
        return this._server;
    }

    set server(value) {
        this._server = value;
    }
    setServer(_server){
        this.server =_server;
    }

    async getServer() {
        const childrenData = await this.getChildNodeList();
        let serverAddress = childrenData[Math.floor((Math.random() * childrenData.length))];

        if (!this.server) { // 初始化
            const path = `${this.parentPath}/${serverAddress}`;
            this.preNodeList = childrenData;
            return await this.getChildNodeData(path);
        }
        if (childrenData.length > this.preNodeList.length) { // 当前的节点个数大于上一次的节点
            this.preNodeList = childrenData;
            if (this.server.connectionStatus === 2) {   // 当前为断开 需要重连
                const path = `${this.parentPath}/${serverAddress}`;
                return await this.getChildNodeData(path);
            } else if (this.server.connectionStatus === 1) {  // 增加新节点不处理

            }
        } else if (childrenData.length < this.preNodeList.length && childrenData.length > 0) {      // 当前的节点个数小于上一次的节点 判断删除的节点是否为当前节点
            this.preNodeList = childrenData;
            if (childrenData.indexOf(this.server.getNodeName()) === -1) {
                const path = `${this.parentPath}/${serverAddress}`;
                return await this.getChildNodeData( path);
            } else {  // 删除的不是当前连接的节点

            }
        } else {  // 断开thrift连接
            this.preNodeList = childrenData;
            this.server.close();
        }

    }

    // 获取指定父节点下的子节点列表
    async  getChildNodeList() {
        const childrenData = await this.client.getChildren()
            .unwantedNamespace()
            .setWatcher(this.client, (__client, event)=>{
                const type = event.getType();
                if (type === 4) {        // 子节点变化
                    this.getServer();
                }
            })
            .forPath(this.parentPath);

        if (childrenData.length === 0) {
            if (this.server && typeof this.server === 'object') {
                this.server.close();
            } else {
                console.warn('UserServer not found');
            }
        }
        return childrenData;
    }
    // 获取子节点的数据
    async getChildNodeData(_path) {
        const split = _path.split('/');
        const childData = await
        this.client.getData()
            .unwantedNamespace()
            .setWatcher(this.client, (__client, event)=> {
                const type = event.getType();
                if (type === 3) {        // 节点修改
                    this.getChildNodeData(event.getPath());
                }
            })
            .forPath(_path);
        if (this.server && typeof this.server === 'object') {
            this.server.setNodeName(split[split.length - 1]);
            this.server.setAddress(childData);
        }
        return {data: childData, nodeName: split[split.length - 1]};

    }
}
module.exports = ConnectZk;
