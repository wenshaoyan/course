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

var CacheService_bannerPut_args = function(args) {
  this.key = null;
  this.banners = null;
  if (args) {
    if (args.key !== undefined && args.key !== null) {
      this.key = args.key;
    }
    if (args.banners !== undefined && args.banners !== null) {
      this.banners = new bean_ttypes.BannerList(args.banners);
    }
  }
};
CacheService_bannerPut_args.prototype = {};
CacheService_bannerPut_args.prototype.read = function(input) {
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
        this.key = input.readString();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRUCT) {
        this.banners = new bean_ttypes.BannerList();
        this.banners.read(input);
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

CacheService_bannerPut_args.prototype.write = function(output) {
  output.writeStructBegin('CacheService_bannerPut_args');
  if (this.key !== null && this.key !== undefined) {
    output.writeFieldBegin('key', Thrift.Type.STRING, 1);
    output.writeString(this.key);
    output.writeFieldEnd();
  }
  if (this.banners !== null && this.banners !== undefined) {
    output.writeFieldBegin('banners', Thrift.Type.STRUCT, 2);
    this.banners.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var CacheService_bannerPut_result = function(args) {
};
CacheService_bannerPut_result.prototype = {};
CacheService_bannerPut_result.prototype.read = function(input) {
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
    input.skip(ftype);
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

CacheService_bannerPut_result.prototype.write = function(output) {
  output.writeStructBegin('CacheService_bannerPut_result');
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var CacheService_bannerGet_args = function(args) {
  this.key = null;
  if (args) {
    if (args.key !== undefined && args.key !== null) {
      this.key = args.key;
    }
  }
};
CacheService_bannerGet_args.prototype = {};
CacheService_bannerGet_args.prototype.read = function(input) {
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
        this.key = input.readString();
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

CacheService_bannerGet_args.prototype.write = function(output) {
  output.writeStructBegin('CacheService_bannerGet_args');
  if (this.key !== null && this.key !== undefined) {
    output.writeFieldBegin('key', Thrift.Type.STRING, 1);
    output.writeString(this.key);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var CacheService_bannerGet_result = function(args) {
  this.success = null;
  if (args) {
    if (args.success !== undefined && args.success !== null) {
      this.success = new bean_ttypes.BannerList(args.success);
    }
  }
};
CacheService_bannerGet_result.prototype = {};
CacheService_bannerGet_result.prototype.read = function(input) {
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
      if (ftype == Thrift.Type.STRUCT) {
        this.success = new bean_ttypes.BannerList();
        this.success.read(input);
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

CacheService_bannerGet_result.prototype.write = function(output) {
  output.writeStructBegin('CacheService_bannerGet_result');
  if (this.success !== null && this.success !== undefined) {
    output.writeFieldBegin('success', Thrift.Type.STRUCT, 0);
    this.success.write(output);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

var CacheServiceClient = exports.Client = function(output, pClass) {
    this.output = output;
    this.pClass = pClass;
    this._seqid = 0;
    this._reqs = {};
};
CacheServiceClient.prototype = {};
CacheServiceClient.prototype.seqid = function() { return this._seqid; };
CacheServiceClient.prototype.new_seqid = function() { return this._seqid += 1; };
CacheServiceClient.prototype.bannerPut = function(key, banners, callback) {
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
    this.send_bannerPut(key, banners);
    return _defer.promise;
  } else {
    this._reqs[this.seqid()] = callback;
    this.send_bannerPut(key, banners);
  }
};

CacheServiceClient.prototype.send_bannerPut = function(key, banners) {
  var output = new this.pClass(this.output);
  output.writeMessageBegin('bannerPut', Thrift.MessageType.CALL, this.seqid());
  var args = new CacheService_bannerPut_args();
  args.key = key;
  args.banners = banners;
  args.write(output);
  output.writeMessageEnd();
  return this.output.flush();
};

CacheServiceClient.prototype.recv_bannerPut = function(input,mtype,rseqid) {
  var callback = this._reqs[rseqid] || function() {};
  delete this._reqs[rseqid];
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(input);
    input.readMessageEnd();
    return callback(x);
  }
  var result = new CacheService_bannerPut_result();
  result.read(input);
  input.readMessageEnd();

  callback(null);
};
CacheServiceClient.prototype.bannerGet = function(key, callback) {
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
    this.send_bannerGet(key);
    return _defer.promise;
  } else {
    this._reqs[this.seqid()] = callback;
    this.send_bannerGet(key);
  }
};

CacheServiceClient.prototype.send_bannerGet = function(key) {
  var output = new this.pClass(this.output);
  output.writeMessageBegin('bannerGet', Thrift.MessageType.CALL, this.seqid());
  var args = new CacheService_bannerGet_args();
  args.key = key;
  args.write(output);
  output.writeMessageEnd();
  return this.output.flush();
};

CacheServiceClient.prototype.recv_bannerGet = function(input,mtype,rseqid) {
  var callback = this._reqs[rseqid] || function() {};
  delete this._reqs[rseqid];
  if (mtype == Thrift.MessageType.EXCEPTION) {
    var x = new Thrift.TApplicationException();
    x.read(input);
    input.readMessageEnd();
    return callback(x);
  }
  var result = new CacheService_bannerGet_result();
  result.read(input);
  input.readMessageEnd();

  if (null !== result.success) {
    return callback(null, result.success);
  }
  return callback('bannerGet failed: unknown result');
};
var CacheServiceProcessor = exports.Processor = function(handler) {
  this._handler = handler;
}
;
CacheServiceProcessor.prototype.process = function(input, output) {
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
CacheServiceProcessor.prototype.process_bannerPut = function(seqid, input, output) {
  var args = new CacheService_bannerPut_args();
  args.read(input);
  input.readMessageEnd();
  if (this._handler.bannerPut.length === 2) {
    Q.fcall(this._handler.bannerPut, args.key, args.banners)
      .then(function(result) {
        var result_obj = new CacheService_bannerPut_result({success: result});
        output.writeMessageBegin("bannerPut", Thrift.MessageType.REPLY, seqid);
        result_obj.write(output);
        output.writeMessageEnd();
        output.flush();
      }, function (err) {
        var result;
        result = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("bannerPut", Thrift.MessageType.EXCEPTION, seqid);
        result.write(output);
        output.writeMessageEnd();
        output.flush();
      });
  } else {
    this._handler.bannerPut(args.key, args.banners, function (err, result) {
      var result_obj;
      if ((err === null || typeof err === 'undefined')) {
        result_obj = new CacheService_bannerPut_result((err !== null || typeof err === 'undefined') ? err : {success: result});
        output.writeMessageBegin("bannerPut", Thrift.MessageType.REPLY, seqid);
      } else {
        result_obj = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("bannerPut", Thrift.MessageType.EXCEPTION, seqid);
      }
      result_obj.write(output);
      output.writeMessageEnd();
      output.flush();
    });
  }
};
CacheServiceProcessor.prototype.process_bannerGet = function(seqid, input, output) {
  var args = new CacheService_bannerGet_args();
  args.read(input);
  input.readMessageEnd();
  if (this._handler.bannerGet.length === 1) {
    Q.fcall(this._handler.bannerGet, args.key)
      .then(function(result) {
        var result_obj = new CacheService_bannerGet_result({success: result});
        output.writeMessageBegin("bannerGet", Thrift.MessageType.REPLY, seqid);
        result_obj.write(output);
        output.writeMessageEnd();
        output.flush();
      }, function (err) {
        var result;
        result = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("bannerGet", Thrift.MessageType.EXCEPTION, seqid);
        result.write(output);
        output.writeMessageEnd();
        output.flush();
      });
  } else {
    this._handler.bannerGet(args.key, function (err, result) {
      var result_obj;
      if ((err === null || typeof err === 'undefined')) {
        result_obj = new CacheService_bannerGet_result((err !== null || typeof err === 'undefined') ? err : {success: result});
        output.writeMessageBegin("bannerGet", Thrift.MessageType.REPLY, seqid);
      } else {
        result_obj = new Thrift.TApplicationException(Thrift.TApplicationExceptionType.UNKNOWN, err.message);
        output.writeMessageBegin("bannerGet", Thrift.MessageType.EXCEPTION, seqid);
      }
      result_obj.write(output);
      output.writeMessageEnd();
      output.flush();
    });
  }
};
