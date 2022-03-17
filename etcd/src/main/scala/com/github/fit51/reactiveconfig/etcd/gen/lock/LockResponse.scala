// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.fit51.reactiveconfig.etcd.gen.lock

/** @param key
  *   key is a key that will exist on etcd for the duration that the Lock caller
  *   owns the lock. Users should not modify this key or the lock may exhibit
  *   undefined behavior.
  */
@SerialVersionUID(0L)
final case class LockResponse(
    header: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] = _root_.scala.None,
    key: _root_.com.google.protobuf.ByteString = _root_.com.google.protobuf.ByteString.EMPTY,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[LockResponse] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      if (header.isDefined) {
        val __value = header.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      
      {
        val __value = key
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBytesSize(2, __value)
        }
      };
      __size += unknownFields.serializedSize
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      header.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      {
        val __v = key
        if (!__v.isEmpty) {
          _output__.writeBytes(2, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def getHeader: com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader = header.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader.defaultInstance)
    def clearHeader: LockResponse = copy(header = _root_.scala.None)
    def withHeader(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader): LockResponse = copy(header = Option(__v))
    def withKey(__v: _root_.com.google.protobuf.ByteString): LockResponse = copy(key = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => header.orNull
        case 2 => {
          val __t = key
          if (__t != _root_.com.google.protobuf.ByteString.EMPTY) __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => header.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => _root_.scalapb.descriptors.PByteString(key)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse
    // @@protoc_insertion_point(GeneratedMessage[v3lockpb.LockResponse])
}

object LockResponse extends scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse = {
    var __header: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] = _root_.scala.None
    var __key: _root_.com.google.protobuf.ByteString = _root_.com.google.protobuf.ByteString.EMPTY
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __header = Option(__header.fold(_root_.scalapb.LiteParser.readMessage[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader](_input__))(_root_.scalapb.LiteParser.readMessage(_input__, _)))
        case 18 =>
          __key = _input__.readBytes()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse(
        header = __header,
        key = __key,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse(
        header = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader]]),
        key = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.com.google.protobuf.ByteString]).getOrElse(_root_.com.google.protobuf.ByteString.EMPTY)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = LockProto.javaDescriptor.getMessageTypes().get(1)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = LockProto.scalaDescriptor.messages(1)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse(
    header = _root_.scala.None,
    key = _root_.com.google.protobuf.ByteString.EMPTY
  )
  implicit class LockResponseLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse](_l) {
    def header: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] = field(_.getHeader)((c_, f_) => c_.copy(header = Option(f_)))
    def optionalHeader: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader]] = field(_.header)((c_, f_) => c_.copy(header = f_))
    def key: _root_.scalapb.lenses.Lens[UpperPB, _root_.com.google.protobuf.ByteString] = field(_.key)((c_, f_) => c_.copy(key = f_))
  }
  final val HEADER_FIELD_NUMBER = 1
  final val KEY_FIELD_NUMBER = 2
  def of(
    header: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader],
    key: _root_.com.google.protobuf.ByteString
  ): _root_.com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse = _root_.com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse(
    header,
    key
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[v3lockpb.LockResponse])
}
