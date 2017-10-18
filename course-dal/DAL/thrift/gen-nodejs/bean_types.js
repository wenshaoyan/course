//
// Autogenerated by Thrift Compiler (0.10.0)
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//
"use strict";

var thrift = require('thrift');
var Thrift = thrift.Thrift;
var Q = thrift.Q;


var ttypes = module.exports = {};
var User = module.exports.User = function(args) {
  this.id = null;
  this.name = null;
  this.head = null;
  this.tel = null;
  this.device_uuid = null;
  this.register_time = null;
  this.create_time = null;
  this.update_time = null;
  this.password = null;
  if (args) {
    if (args.id !== undefined && args.id !== null) {
      this.id = args.id;
    }
    if (args.name !== undefined && args.name !== null) {
      this.name = args.name;
    }
    if (args.head !== undefined && args.head !== null) {
      this.head = args.head;
    }
    if (args.tel !== undefined && args.tel !== null) {
      this.tel = args.tel;
    }
    if (args.device_uuid !== undefined && args.device_uuid !== null) {
      this.device_uuid = args.device_uuid;
    }
    if (args.register_time !== undefined && args.register_time !== null) {
      this.register_time = args.register_time;
    }
    if (args.create_time !== undefined && args.create_time !== null) {
      this.create_time = args.create_time;
    }
    if (args.update_time !== undefined && args.update_time !== null) {
      this.update_time = args.update_time;
    }
    if (args.password !== undefined && args.password !== null) {
      this.password = args.password;
    }
  }
};
User.prototype = {};
User.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I32) {
        this.id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.name = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.STRING) {
        this.head = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.STRING) {
        this.tel = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.STRING) {
        this.device_uuid = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 6:
      if (ftype == Thrift.Type.STRING) {
        this.register_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 7:
      if (ftype == Thrift.Type.STRING) {
        this.create_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 8:
      if (ftype == Thrift.Type.STRING) {
        this.update_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 9:
      if (ftype == Thrift.Type.STRING) {
        this.password = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

User.prototype.write = function(output) {
  output.writeStructBegin('User');
  if (this.id !== null && this.id !== undefined) {
    output.writeFieldBegin('id', Thrift.Type.I32, 1);
    output.writeI32(this.id);
    output.writeFieldEnd();
  }
  if (this.name !== null && this.name !== undefined) {
    output.writeFieldBegin('name', Thrift.Type.STRING, 2);
    output.writeString(this.name);
    output.writeFieldEnd();
  }
  if (this.head !== null && this.head !== undefined) {
    output.writeFieldBegin('head', Thrift.Type.STRING, 3);
    output.writeString(this.head);
    output.writeFieldEnd();
  }
  if (this.tel !== null && this.tel !== undefined) {
    output.writeFieldBegin('tel', Thrift.Type.STRING, 4);
    output.writeString(this.tel);
    output.writeFieldEnd();
  }
  if (this.device_uuid !== null && this.device_uuid !== undefined) {
    output.writeFieldBegin('device_uuid', Thrift.Type.STRING, 5);
    output.writeString(this.device_uuid);
    output.writeFieldEnd();
  }
  if (this.register_time !== null && this.register_time !== undefined) {
    output.writeFieldBegin('register_time', Thrift.Type.STRING, 6);
    output.writeString(this.register_time);
    output.writeFieldEnd();
  }
  if (this.create_time !== null && this.create_time !== undefined) {
    output.writeFieldBegin('create_time', Thrift.Type.STRING, 7);
    output.writeString(this.create_time);
    output.writeFieldEnd();
  }
  if (this.update_time !== null && this.update_time !== undefined) {
    output.writeFieldBegin('update_time', Thrift.Type.STRING, 8);
    output.writeString(this.update_time);
    output.writeFieldEnd();
  }
  if (this.password !== null && this.password !== undefined) {
    output.writeFieldBegin('password', Thrift.Type.STRING, 9);
    output.writeString(this.password);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var Banner = module.exports.Banner = function(args) {
  this.id = null;
  this.create_time = null;
  this.update_time = null;
  this.user_id = null;
  this.image_url = null;
  this.redirect_url = null;
  this.show_client_id = null;
  this.location = null;
  if (args) {
    if (args.id !== undefined && args.id !== null) {
      this.id = args.id;
    }
    if (args.create_time !== undefined && args.create_time !== null) {
      this.create_time = args.create_time;
    }
    if (args.update_time !== undefined && args.update_time !== null) {
      this.update_time = args.update_time;
    }
    if (args.user_id !== undefined && args.user_id !== null) {
      this.user_id = args.user_id;
    }
    if (args.image_url !== undefined && args.image_url !== null) {
      this.image_url = args.image_url;
    }
    if (args.redirect_url !== undefined && args.redirect_url !== null) {
      this.redirect_url = args.redirect_url;
    }
    if (args.show_client_id !== undefined && args.show_client_id !== null) {
      this.show_client_id = args.show_client_id;
    }
    if (args.location !== undefined && args.location !== null) {
      this.location = args.location;
    }
  }
};
Banner.prototype = {};
Banner.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I32) {
        this.id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.create_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.STRING) {
        this.update_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.I32) {
        this.user_id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.STRING) {
        this.image_url = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 6:
      if (ftype == Thrift.Type.STRING) {
        this.redirect_url = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 7:
      if (ftype == Thrift.Type.I32) {
        this.show_client_id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 8:
      if (ftype == Thrift.Type.I32) {
        this.location = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

Banner.prototype.write = function(output) {
  output.writeStructBegin('Banner');
  if (this.id !== null && this.id !== undefined) {
    output.writeFieldBegin('id', Thrift.Type.I32, 1);
    output.writeI32(this.id);
    output.writeFieldEnd();
  }
  if (this.create_time !== null && this.create_time !== undefined) {
    output.writeFieldBegin('create_time', Thrift.Type.STRING, 2);
    output.writeString(this.create_time);
    output.writeFieldEnd();
  }
  if (this.update_time !== null && this.update_time !== undefined) {
    output.writeFieldBegin('update_time', Thrift.Type.STRING, 3);
    output.writeString(this.update_time);
    output.writeFieldEnd();
  }
  if (this.user_id !== null && this.user_id !== undefined) {
    output.writeFieldBegin('user_id', Thrift.Type.I32, 4);
    output.writeI32(this.user_id);
    output.writeFieldEnd();
  }
  if (this.image_url !== null && this.image_url !== undefined) {
    output.writeFieldBegin('image_url', Thrift.Type.STRING, 5);
    output.writeString(this.image_url);
    output.writeFieldEnd();
  }
  if (this.redirect_url !== null && this.redirect_url !== undefined) {
    output.writeFieldBegin('redirect_url', Thrift.Type.STRING, 6);
    output.writeString(this.redirect_url);
    output.writeFieldEnd();
  }
  if (this.show_client_id !== null && this.show_client_id !== undefined) {
    output.writeFieldBegin('show_client_id', Thrift.Type.I32, 7);
    output.writeI32(this.show_client_id);
    output.writeFieldEnd();
  }
  if (this.location !== null && this.location !== undefined) {
    output.writeFieldBegin('location', Thrift.Type.I32, 8);
    output.writeI32(this.location);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var ClientVersion = module.exports.ClientVersion = function(args) {
  this.id = null;
  this.create_time = null;
  this.update_time = null;
  this.version_name = null;
  this.version_number = null;
  this.client = null;
  this.download_url = null;
  if (args) {
    if (args.id !== undefined && args.id !== null) {
      this.id = args.id;
    }
    if (args.create_time !== undefined && args.create_time !== null) {
      this.create_time = args.create_time;
    }
    if (args.update_time !== undefined && args.update_time !== null) {
      this.update_time = args.update_time;
    }
    if (args.version_name !== undefined && args.version_name !== null) {
      this.version_name = args.version_name;
    }
    if (args.version_number !== undefined && args.version_number !== null) {
      this.version_number = args.version_number;
    }
    if (args.client !== undefined && args.client !== null) {
      this.client = args.client;
    }
    if (args.download_url !== undefined && args.download_url !== null) {
      this.download_url = args.download_url;
    }
  }
};
ClientVersion.prototype = {};
ClientVersion.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I32) {
        this.id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.create_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.STRING) {
        this.update_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.STRING) {
        this.version_name = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.STRING) {
        this.version_number = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 6:
      if (ftype == Thrift.Type.I32) {
        this.client = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 7:
      if (ftype == Thrift.Type.STRING) {
        this.download_url = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

ClientVersion.prototype.write = function(output) {
  output.writeStructBegin('ClientVersion');
  if (this.id !== null && this.id !== undefined) {
    output.writeFieldBegin('id', Thrift.Type.I32, 1);
    output.writeI32(this.id);
    output.writeFieldEnd();
  }
  if (this.create_time !== null && this.create_time !== undefined) {
    output.writeFieldBegin('create_time', Thrift.Type.STRING, 2);
    output.writeString(this.create_time);
    output.writeFieldEnd();
  }
  if (this.update_time !== null && this.update_time !== undefined) {
    output.writeFieldBegin('update_time', Thrift.Type.STRING, 3);
    output.writeString(this.update_time);
    output.writeFieldEnd();
  }
  if (this.version_name !== null && this.version_name !== undefined) {
    output.writeFieldBegin('version_name', Thrift.Type.STRING, 4);
    output.writeString(this.version_name);
    output.writeFieldEnd();
  }
  if (this.version_number !== null && this.version_number !== undefined) {
    output.writeFieldBegin('version_number', Thrift.Type.STRING, 5);
    output.writeString(this.version_number);
    output.writeFieldEnd();
  }
  if (this.client !== null && this.client !== undefined) {
    output.writeFieldBegin('client', Thrift.Type.I32, 6);
    output.writeI32(this.client);
    output.writeFieldEnd();
  }
  if (this.download_url !== null && this.download_url !== undefined) {
    output.writeFieldBegin('download_url', Thrift.Type.STRING, 7);
    output.writeString(this.download_url);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var ClientSide = module.exports.ClientSide = function(args) {
  this.id = null;
  this.create_time = null;
  this.update_time = null;
  this.name = null;
  this.package_name = null;
  if (args) {
    if (args.id !== undefined && args.id !== null) {
      this.id = args.id;
    }
    if (args.create_time !== undefined && args.create_time !== null) {
      this.create_time = args.create_time;
    }
    if (args.update_time !== undefined && args.update_time !== null) {
      this.update_time = args.update_time;
    }
    if (args.name !== undefined && args.name !== null) {
      this.name = args.name;
    }
    if (args.package_name !== undefined && args.package_name !== null) {
      this.package_name = args.package_name;
    }
  }
};
ClientSide.prototype = {};
ClientSide.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I32) {
        this.id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.create_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.STRING) {
        this.update_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.STRING) {
        this.name = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.STRING) {
        this.package_name = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

ClientSide.prototype.write = function(output) {
  output.writeStructBegin('ClientSide');
  if (this.id !== null && this.id !== undefined) {
    output.writeFieldBegin('id', Thrift.Type.I32, 1);
    output.writeI32(this.id);
    output.writeFieldEnd();
  }
  if (this.create_time !== null && this.create_time !== undefined) {
    output.writeFieldBegin('create_time', Thrift.Type.STRING, 2);
    output.writeString(this.create_time);
    output.writeFieldEnd();
  }
  if (this.update_time !== null && this.update_time !== undefined) {
    output.writeFieldBegin('update_time', Thrift.Type.STRING, 3);
    output.writeString(this.update_time);
    output.writeFieldEnd();
  }
  if (this.name !== null && this.name !== undefined) {
    output.writeFieldBegin('name', Thrift.Type.STRING, 4);
    output.writeString(this.name);
    output.writeFieldEnd();
  }
  if (this.package_name !== null && this.package_name !== undefined) {
    output.writeFieldBegin('package_name', Thrift.Type.STRING, 5);
    output.writeString(this.package_name);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var BannerList = module.exports.BannerList = function(args) {
  this.data = null;
  if (args) {
    if (args.data !== undefined && args.data !== null) {
      this.data = Thrift.copyList(args.data, [ttypes.Banner]);
    }
  }
};
BannerList.prototype = {};
BannerList.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.LIST) {
        var _size0 = 0;
        var _rtmp34;
        this.data = [];
        var _etype3 = 0;
        _rtmp34 = input.readListBegin();
        _etype3 = _rtmp34.etype;
        _size0 = _rtmp34.size;
        for (var _i5 = 0; _i5 < _size0; ++_i5)
        {
          var elem6 = null;
          elem6 = new ttypes.Banner();
          elem6.read(input);
          this.data.push(elem6);
        }
        input.readListEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

BannerList.prototype.write = function(output) {
  output.writeStructBegin('BannerList');
  if (this.data !== null && this.data !== undefined) {
    output.writeFieldBegin('data', Thrift.Type.LIST, 1);
    output.writeListBegin(Thrift.Type.STRUCT, this.data.length);
    for (var iter7 in this.data)
    {
      if (this.data.hasOwnProperty(iter7))
      {
        iter7 = this.data[iter7];
        iter7.write(output);
      }
    }
    output.writeListEnd();
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var Course = module.exports.Course = function(args) {
  this.id = null;
  this.title = null;
  this.create_time = null;
  this.price = null;
  this.describe = null;
  this.status = null;
  this.author_id = null;
  this.type = null;
  this.image = null;
  if (args) {
    if (args.id !== undefined && args.id !== null) {
      this.id = args.id;
    }
    if (args.title !== undefined && args.title !== null) {
      this.title = args.title;
    }
    if (args.create_time !== undefined && args.create_time !== null) {
      this.create_time = args.create_time;
    }
    if (args.price !== undefined && args.price !== null) {
      this.price = args.price;
    }
    if (args.describe !== undefined && args.describe !== null) {
      this.describe = args.describe;
    }
    if (args.status !== undefined && args.status !== null) {
      this.status = args.status;
    }
    if (args.author_id !== undefined && args.author_id !== null) {
      this.author_id = args.author_id;
    }
    if (args.type !== undefined && args.type !== null) {
      this.type = args.type;
    }
    if (args.image !== undefined && args.image !== null) {
      this.image = args.image;
    }
  }
};
Course.prototype = {};
Course.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.I32) {
        this.id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.title = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.STRING) {
        this.create_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.DOUBLE) {
        this.price = input.readDouble();
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.STRING) {
        this.describe = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 6:
      if (ftype == Thrift.Type.I32) {
        this.status = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 7:
      if (ftype == Thrift.Type.I32) {
        this.author_id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 8:
      if (ftype == Thrift.Type.I32) {
        this.type = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 9:
      if (ftype == Thrift.Type.STRING) {
        this.image = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

Course.prototype.write = function(output) {
  output.writeStructBegin('Course');
  if (this.id !== null && this.id !== undefined) {
    output.writeFieldBegin('id', Thrift.Type.I32, 1);
    output.writeI32(this.id);
    output.writeFieldEnd();
  }
  if (this.title !== null && this.title !== undefined) {
    output.writeFieldBegin('title', Thrift.Type.STRING, 2);
    output.writeString(this.title);
    output.writeFieldEnd();
  }
  if (this.create_time !== null && this.create_time !== undefined) {
    output.writeFieldBegin('create_time', Thrift.Type.STRING, 3);
    output.writeString(this.create_time);
    output.writeFieldEnd();
  }
  if (this.price !== null && this.price !== undefined) {
    output.writeFieldBegin('price', Thrift.Type.DOUBLE, 4);
    output.writeDouble(this.price);
    output.writeFieldEnd();
  }
  if (this.describe !== null && this.describe !== undefined) {
    output.writeFieldBegin('describe', Thrift.Type.STRING, 5);
    output.writeString(this.describe);
    output.writeFieldEnd();
  }
  if (this.status !== null && this.status !== undefined) {
    output.writeFieldBegin('status', Thrift.Type.I32, 6);
    output.writeI32(this.status);
    output.writeFieldEnd();
  }
  if (this.author_id !== null && this.author_id !== undefined) {
    output.writeFieldBegin('author_id', Thrift.Type.I32, 7);
    output.writeI32(this.author_id);
    output.writeFieldEnd();
  }
  if (this.type !== null && this.type !== undefined) {
    output.writeFieldBegin('type', Thrift.Type.I32, 8);
    output.writeI32(this.type);
    output.writeFieldEnd();
  }
  if (this.image !== null && this.image !== undefined) {
    output.writeFieldBegin('image', Thrift.Type.STRING, 9);
    output.writeString(this.image);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var Video = module.exports.Video = function(args) {
  this.id = null;
  this.title = null;
  this.resource_path = null;
  this.resource_time = null;
  this.location = null;
  this.course_id = null;
  this.status = null;
  this.play_number = null;
  this.create_time = null;
  this.update_time = null;
  if (args) {
    if (args.id !== undefined && args.id !== null) {
      this.id = args.id;
    }
    if (args.title !== undefined && args.title !== null) {
      this.title = args.title;
    }
    if (args.resource_path !== undefined && args.resource_path !== null) {
      this.resource_path = args.resource_path;
    }
    if (args.resource_time !== undefined && args.resource_time !== null) {
      this.resource_time = args.resource_time;
    }
    if (args.location !== undefined && args.location !== null) {
      this.location = args.location;
    }
    if (args.course_id !== undefined && args.course_id !== null) {
      this.course_id = args.course_id;
    }
    if (args.status !== undefined && args.status !== null) {
      this.status = args.status;
    }
    if (args.play_number !== undefined && args.play_number !== null) {
      this.play_number = args.play_number;
    }
    if (args.create_time !== undefined && args.create_time !== null) {
      this.create_time = args.create_time;
    }
    if (args.update_time !== undefined && args.update_time !== null) {
      this.update_time = args.update_time;
    }
  }
};
Video.prototype = {};
Video.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRING) {
        this.id = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.title = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.STRING) {
        this.resource_path = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.I32) {
        this.resource_time = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 5:
      if (ftype == Thrift.Type.I32) {
        this.location = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 6:
      if (ftype == Thrift.Type.I32) {
        this.course_id = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 7:
      if (ftype == Thrift.Type.I32) {
        this.status = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 8:
      if (ftype == Thrift.Type.I32) {
        this.play_number = input.readI32();
      } else {
        input.skip(ftype);
      }
      break;
      case 9:
      if (ftype == Thrift.Type.STRING) {
        this.create_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 10:
      if (ftype == Thrift.Type.STRING) {
        this.update_time = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

Video.prototype.write = function(output) {
  output.writeStructBegin('Video');
  if (this.id !== null && this.id !== undefined) {
    output.writeFieldBegin('id', Thrift.Type.STRING, 1);
    output.writeString(this.id);
    output.writeFieldEnd();
  }
  if (this.title !== null && this.title !== undefined) {
    output.writeFieldBegin('title', Thrift.Type.STRING, 2);
    output.writeString(this.title);
    output.writeFieldEnd();
  }
  if (this.resource_path !== null && this.resource_path !== undefined) {
    output.writeFieldBegin('resource_path', Thrift.Type.STRING, 3);
    output.writeString(this.resource_path);
    output.writeFieldEnd();
  }
  if (this.resource_time !== null && this.resource_time !== undefined) {
    output.writeFieldBegin('resource_time', Thrift.Type.I32, 4);
    output.writeI32(this.resource_time);
    output.writeFieldEnd();
  }
  if (this.location !== null && this.location !== undefined) {
    output.writeFieldBegin('location', Thrift.Type.I32, 5);
    output.writeI32(this.location);
    output.writeFieldEnd();
  }
  if (this.course_id !== null && this.course_id !== undefined) {
    output.writeFieldBegin('course_id', Thrift.Type.I32, 6);
    output.writeI32(this.course_id);
    output.writeFieldEnd();
  }
  if (this.status !== null && this.status !== undefined) {
    output.writeFieldBegin('status', Thrift.Type.I32, 7);
    output.writeI32(this.status);
    output.writeFieldEnd();
  }
  if (this.play_number !== null && this.play_number !== undefined) {
    output.writeFieldBegin('play_number', Thrift.Type.I32, 8);
    output.writeI32(this.play_number);
    output.writeFieldEnd();
  }
  if (this.create_time !== null && this.create_time !== undefined) {
    output.writeFieldBegin('create_time', Thrift.Type.STRING, 9);
    output.writeString(this.create_time);
    output.writeFieldEnd();
  }
  if (this.update_time !== null && this.update_time !== undefined) {
    output.writeFieldBegin('update_time', Thrift.Type.STRING, 10);
    output.writeString(this.update_time);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

