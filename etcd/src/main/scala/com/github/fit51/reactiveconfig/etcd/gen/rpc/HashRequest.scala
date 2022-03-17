// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.fit51.reactiveconfig.etcd.gen.rpc

@SerialVersionUID(0L)
final case class HashRequest(
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[HashRequest] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
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
      unknownFields.writeTo(_output__)
    }
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = throw new MatchError(__fieldNumber)
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = throw new MatchError(__field)
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest
    // @@protoc_insertion_point(GeneratedMessage[etcdserverpb.HashRequest])
}

object HashRequest extends scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest = {
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest(
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest(
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = RpcProto.javaDescriptor.getMessageTypes().get(14)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = RpcProto.scalaDescriptor.messages(14)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest(
  )
  implicit class HashRequestLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest](_l) {
  }
  def of(
  ): _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest = _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.HashRequest(
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[etcdserverpb.HashRequest])
}
