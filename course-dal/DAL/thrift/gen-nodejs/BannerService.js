//
// Autogenerated by Thrift Compiler (0.10.0)
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//
"use strict";

var thrift = require('thrift');
var Thrift = thrift.Thrift;
var Q = thrift.Q;

var bean_ttypes = require('./bean_types');


var ttypes = require('./service_types');
//HELPER FUNCTIONS AND STRUCTURES

var BannerService_insert_args = function(args) {
  this.banner = null;
  if (args) {
    if (args.banner !== undefined && args.banner !== null) {
      this.banner = new bean_ttypes.Banner(args.banner);
    }
  }
};
BannerService_insert_args.prototype = {};
BannerService_insert_args.prototype.read = function(input) {
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
      if (ftype == Thrift.Type.STRUCT) {
        this.banner = new bean_ttypes.Banner();
        this.banner.read(input);
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

BannerService_insert_args.prototype.write = function(output) {
  output.writeStructBegin('BannerService_insert_args');
  if (this.banner !== null && this.banner !== undefined) {
    output.writeFieldBegin('banner', Thrift.Type.STRUCT, 1);
    this.banner.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var BannerService_insert_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined && args.success !== null) {
      this.success = args.success;
    }
  }
};
BannerService_insert_result.prototype = {};
BannerService_insert_result.prototype.read = function(input) {
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
      case 0:
      if (ftype == Thrift.Type.I16) {
        this.success = input.readI16();
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

BannerService_insert_result.prototype.write = function(output) {
  output.writeStructBegin('BannerService_insert_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.I16, 0);
    output.writeI16(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var BannerService_update_args = function(args) {
  this.banner = null;
  if (args) {
    if (args.banner !== undefined && args.banner !== null) {
      this.banner = new bean_ttypes.Banner(args.banner);
    }
  }
};
BannerService_update_args.prototype = {};
BannerService_update_args.prototype.read = function(input) {
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
      if (ftype == Thrift.Type.STRUCT) {
        this.banner = new bean_ttypes.Banner();
        this.banner.read(input);
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

BannerService_update_args.prototype.write = function(output) {
  output.writeStructBegin('BannerService_update_args');
  if (this.banner !== null && this.banner !== undefined) {
    output.writeFieldBegin('banner', Thrift.Type.STRUCT, 1);
    this.banner.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var BannerService_update_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined && args.success !== null) {
      this.success = args.success;
    }
  }
};
BannerService_update_result.prototype = {};
BannerService_update_result.prototype.read = function(input) {
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
      case 0:
      if (ftype == Thrift.Type.I16) {
        this.success = input.readI16();
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

BannerService_update_result.prototype.write = function(output) {
  output.writeStructBegin('BannerService_update_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.I16, 0);
    output.writeI16(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var BannerService_remove_args = function(args) {
  this.id = null;
  if (args) {
    if (args.id !== undefined && args.id !== null) {
      this.id = args.id;
    }
  }
};
BannerService_remove_args.prototype = {};
BannerService_remove_args.prototype.read = function(input) {
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
      if (ftype == Thrift.Type.I16) {
        this.id = input.readI16();
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

BannerService_remove_args.prototype.write = function(output) {
  output.writeStructBegin('BannerService_remove_args');
  if (this.id !== null && this.id !== undefined) {
    output.writeFieldBegin('id', Thrift.Type.I16, 1);
    output.writeI16(this.id);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var BannerService_remove_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined && args.success !== null) {
      this.success = args.success;
    }
  }
};
BannerService_remove_result.prototype = {};
BannerService_remove_result.prototype.read = function(input) {
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
      case 0:
      if (ftype == Thrift.Type.I16) {
        this.success = input.readI16();
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

BannerService_remove_result.prototype.write = function(output) {
  output.writeStructBegin('BannerService_remove_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.I16, 0);
    output.writeI16(this.success);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var BannerServiceClient = exports.Client = function(output, pClass) {
    this.output = output;
    this.pClass = pClass;
    this._seqid = 0;
    this._reqs = {};
};
BannerServiceClient.prototype = {};
BannerServiceClient.prototype.seqid = function() { return this._seqid; };
BannerServiceClient.prototype.new_seqid = function() { return this._seqid += 1; };
BannerServiceClient.prototype.insert = function(banner, callback) {
  this._seqid = this.new_seqid();
  if (callback === undefined) {
    var _defer = Q.defer();
    this._reqs[this.seqid()] = function(error, result) {
      if (error) {
        _defer.reject(error);
      } else {
        _defer.resolve(result);
      }
    };
    this.send_insert(banner);
    return _defer.promise;
  } else {
    this._reqs[this.seqid()] = callback;
    this.send_insert(banner);
  }
};

BannerServiceClient.prototype.send_insert = function(banner) {
  var output = new this.pClass(this.output);
  output.writeMessageBegin('insert', Thrift.MessageType.CALL, this.seqid());
  var args = new BannerService_insert_args();
  args.banner = banner;
  args.write(output);
  output.writeMessageEnd();
  return this.output.flush();
};

BannerServiceClient.prototype.recv_insert = function(input,mtype,rseqid) {
  var callback = this._reqs[rseqid] || function() {};
  delete this._reqs[rseqid];
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(input);
    input.readMessageEnd();
    return callback(x);
  }
  var result = new BannerService_insert_result();
  result.read(input);
  input.readMessageEnd();

  if (null !== result.success) {
    return callback(null, result.success);
  }
  return callback('insert failed: unknown result');
};
BannerServiceClient.prototype.update = function(banner, callback) {
  this._seqid = this.new_seqid();
  if (callback === undefined) {
    var _defer = Q.defer();
    this._reqs[this.seqid()] = function(error, result) {
      if (error) {
        _defer.reject(error);
      } else {
        _defer.resolve(result);
      }
    };
    this.send_update(banner);
    return _defer.promise;
  } else {
    this._reqs[this.seqid()] = callback;
    this.send_update(banner);
  }
};

BannerServiceClient.prototype.send_update = function(banner) {
  var output = new this.pClass(this.output);
  output.writeMessageBegin('update', Thrift.MessageType.CALL, this.seqid());
  var args = new BannerService_update_args();
  args.banner = banner;
  args.write(output);
  output.writeMessageEnd();
  return this.output.flush();
};

BannerServiceClient.prototype.recv_update = function(input,mtype,rseqid) {
  var callback = this._reqs[rseqid] || function() {};
  delete this._reqs[rseqid];
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(input);
    input.readMessageEnd();
    return callback(x);
  }
  var result = new BannerService_update_result();
  result.read(input);
  input.readMessageEnd();

  if (null !== result.success) {
    return callback(null, result.success);
  }
  return callback('update failed: unknown result');
};
BannerServiceClient.prototype.remove = function(id, callback) {
  this._seqid = this.new_seqid();
  if (callback === undefined) {
    var _defer = Q.defer();
    this._reqs[this.seqid()] = function(error, result) {
      if (error) {
        _defer.reject(error);
      } else {
        _defer.resolve(result);
      }
    };
    this.send_remove(id);
    return _defer.promise;
  } else {
    this._reqs[this.seqid()] = callback;
    this.send_remove(id);
  }
};

BannerServiceClient.prototype.send_remove = function(id) {
  var output = new this.pClass(this.output);
  output.writeMessageBegin('remove', Thrift.MessageType.CALL, this.seqid());
  var args = new BannerService_remove_args();
  args.id = id;
  args.write(output);
  output.writeMessageEnd();
  return this.output.flush();
};

BannerServiceClient.prototype.recv_remove = function(input,mtype,rseqid) {
  var callback = this._reqs[rseqid] || function() {};
  delete this._reqs[rseqid];
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(input);
    input.readMessageEnd();
    return callback(x);
  }
  var result = new BannerService_remove_result();
  result.read(input);
  input.readMessageEnd();

  if (null !== result.success) {
    return callback(null, result.success);
  }
  return callback('remove failed: unknown result');
};
var BannerServiceProcessor = exports.Processor = function(handler) {
  this._handler = handler;
}
;
BannerServiceProcessor.prototype.process = function(input, output) {
  var r = input.readMessageBegin();
  if (this['process_' + r.fname]) {
    return this['process_' + r.fname].call(this, r.rseqid, input, output);
  } else {
    input.skip(Thrift.Type.STRUCT);
    input.readMessageEnd();
    var x = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN_METHOD, 'Unknown function ' + r.fname);
    output.writeMessageBegin(r.fname, Thrift.MessageType.EXCEPTION, r.rseqid);
    x.write(output);
    output.writeMessageEnd();
    output.flush();
  }
}
;
BannerServiceProcessor.prototype.process_insert = function(seqid, input, output) {
  var args = new BannerService_insert_args();
  args.read(input);
  input.readMessageEnd();
  if (this._handler.insert.length === 1) {
    Q.fcall(this._handler.insert, args.banner)
      .then(function(result) {
        var result_obj = new BannerService_insert_result({success: result});
        output.writeMessageBegin("insert", Thrift.MessageType.REPLY, seqid);
        result_obj.write(output);
        output.writeMessageEnd();
        output.flush();
      }, function (err) {
        var result;
        result = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("insert", Thrift.MessageType.EXCEPTION, seqid);
        result.write(output);
        output.writeMessageEnd();
        output.flush();
      });
  } else {
    this._handler.insert(args.banner, function (err, result) {
      var result_obj;
      if ((err === null || typeof err === 'undefined')) {
        result_obj = new BannerService_insert_result((err !== null || typeof err === 'undefined') ? err : {success: result});
        output.writeMessageBegin("insert", Thrift.MessageType.REPLY, seqid);
      } else {
        result_obj = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("insert", Thrift.MessageType.EXCEPTION, seqid);
      }
      result_obj.write(output);
      output.writeMessageEnd();
      output.flush();
    });
  }
};
BannerServiceProcessor.prototype.process_update = function(seqid, input, output) {
  var args = new BannerService_update_args();
  args.read(input);
  input.readMessageEnd();
  if (this._handler.update.length === 1) {
    Q.fcall(this._handler.update, args.banner)
      .then(function(result) {
        var result_obj = new BannerService_update_result({success: result});
        output.writeMessageBegin("update", Thrift.MessageType.REPLY, seqid);
        result_obj.write(output);
        output.writeMessageEnd();
        output.flush();
      }, function (err) {
        var result;
        result = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("update", Thrift.MessageType.EXCEPTION, seqid);
        result.write(output);
        output.writeMessageEnd();
        output.flush();
      });
  } else {
    this._handler.update(args.banner, function (err, result) {
      var result_obj;
      if ((err === null || typeof err === 'undefined')) {
        result_obj = new BannerService_update_result((err !== null || typeof err === 'undefined') ? err : {success: result});
        output.writeMessageBegin("update", Thrift.MessageType.REPLY, seqid);
      } else {
        result_obj = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("update", Thrift.MessageType.EXCEPTION, seqid);
      }
      result_obj.write(output);
      output.writeMessageEnd();
      output.flush();
    });
  }
};
BannerServiceProcessor.prototype.process_remove = function(seqid, input, output) {
  var args = new BannerService_remove_args();
  args.read(input);
  input.readMessageEnd();
  if (this._handler.remove.length === 1) {
    Q.fcall(this._handler.remove, args.id)
      .then(function(result) {
        var result_obj = new BannerService_remove_result({success: result});
        output.writeMessageBegin("remove", Thrift.MessageType.REPLY, seqid);
        result_obj.write(output);
        output.writeMessageEnd();
        output.flush();
      }, function (err) {
        var result;
        result = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("remove", Thrift.MessageType.EXCEPTION, seqid);
        result.write(output);
        output.writeMessageEnd();
        output.flush();
      });
  } else {
    this._handler.remove(args.id, function (err, result) {
      var result_obj;
      if ((err === null || typeof err === 'undefined')) {
        result_obj = new BannerService_remove_result((err !== null || typeof err === 'undefined') ? err : {success: result});
        output.writeMessageBegin("remove", Thrift.MessageType.REPLY, seqid);
      } else {
        result_obj = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("remove", Thrift.MessageType.EXCEPTION, seqid);
      }
      result_obj.write(output);
      output.writeMessageEnd();
      output.flush();
    });
  }
};
