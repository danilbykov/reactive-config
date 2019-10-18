// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.fit51.reactiveconfig.etcd.gen.rpc

@SerialVersionUID(0L)
final case class WatchRequest(
    requestUnion: com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.Empty
    ) extends scalapb.GeneratedMessage with scalapb.Message[WatchRequest] with scalapb.lenses.Updatable[WatchRequest] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      if (requestUnion.createRequest.isDefined) {
        val __value = requestUnion.createRequest.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (requestUnion.cancelRequest.isDefined) {
        val __value = requestUnion.cancelRequest.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      __size
    }
    final override def serializedSize: _root_.scala.Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      requestUnion.createRequest.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      requestUnion.cancelRequest.foreach { __v =>
        val __m = __v
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest = {
      var __requestUnion = this.requestUnion
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __requestUnion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CreateRequest(_root_.scalapb.LiteParser.readMessage(_input__, requestUnion.createRequest.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest.defaultInstance)))
          case 18 =>
            __requestUnion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CancelRequest(_root_.scalapb.LiteParser.readMessage(_input__, requestUnion.cancelRequest.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest.defaultInstance)))
          case tag => _input__.skipField(tag)
        }
      }
      com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest(
          requestUnion = __requestUnion
      )
    }
    def getCreateRequest: com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest = requestUnion.createRequest.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest.defaultInstance)
    def withCreateRequest(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest): WatchRequest = copy(requestUnion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CreateRequest(__v))
    def getCancelRequest: com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest = requestUnion.cancelRequest.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest.defaultInstance)
    def withCancelRequest(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest): WatchRequest = copy(requestUnion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CancelRequest(__v))
    def clearRequestUnion: WatchRequest = copy(requestUnion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.Empty)
    def withRequestUnion(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion): WatchRequest = copy(requestUnion = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => requestUnion.createRequest.orNull
        case 2 => requestUnion.cancelRequest.orNull
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => requestUnion.createRequest.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => requestUnion.cancelRequest.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest
}

object WatchRequest extends scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, _root_.scala.Any]): com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest = {
    _root_.scala.Predef.require(__fieldsMap.keys.forall(_.getContainingType() == javaDescriptor), "FieldDescriptor does not match message type.")
    val __fields = javaDescriptor.getFields
    com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest(
      requestUnion = __fieldsMap.get(__fields.get(0)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest]].map(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CreateRequest)
    .orElse[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion](__fieldsMap.get(__fields.get(1)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest]].map(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CancelRequest))
    .getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.Empty)
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage == scalaDescriptor), "FieldDescriptor does not match message type.")
      com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest(
        requestUnion = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest]]).map(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CreateRequest)
    .orElse[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion](__fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest]]).map(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CancelRequest))
    .getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.Empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = RpcProto.javaDescriptor.getMessageTypes.get(18)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = RpcProto.scalaDescriptor.messages(18)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest
      case 2 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest(
    requestUnion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.Empty
  )
  sealed trait RequestUnion extends _root_.scalapb.GeneratedOneof {
    def isEmpty: _root_.scala.Boolean = false
    def isDefined: _root_.scala.Boolean = true
    def isCreateRequest: _root_.scala.Boolean = false
    def isCancelRequest: _root_.scala.Boolean = false
    def createRequest: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest] = _root_.scala.None
    def cancelRequest: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest] = _root_.scala.None
  }
  object RequestUnion extends {
    @SerialVersionUID(0L)
    case object Empty extends com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion {
      type ValueType = _root_.scala.Nothing
      override def isEmpty: _root_.scala.Boolean = true
      override def isDefined: _root_.scala.Boolean = false
      override def number: _root_.scala.Int = 0
      override def value: _root_.scala.Nothing = throw new java.util.NoSuchElementException("Empty.value")
    }
  
    @SerialVersionUID(0L)
    final case class CreateRequest(value: com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest) extends com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion {
      type ValueType = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest
      override def isCreateRequest: _root_.scala.Boolean = true
      override def createRequest: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest] = Some(value)
      override def number: _root_.scala.Int = 1
    }
    @SerialVersionUID(0L)
    final case class CancelRequest(value: com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest) extends com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion {
      type ValueType = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest
      override def isCancelRequest: _root_.scala.Boolean = true
      override def cancelRequest: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest] = Some(value)
      override def number: _root_.scala.Int = 2
    }
  }
  implicit class WatchRequestLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest](_l) {
    def createRequest: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCreateRequest] = field(_.getCreateRequest)((c_, f_) => c_.copy(requestUnion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CreateRequest(f_)))
    def cancelRequest: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchCancelRequest] = field(_.getCancelRequest)((c_, f_) => c_.copy(requestUnion = com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion.CancelRequest(f_)))
    def requestUnion: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion] = field(_.requestUnion)((c_, f_) => c_.copy(requestUnion = f_))
  }
  final val CREATE_REQUEST_FIELD_NUMBER = 1
  final val CANCEL_REQUEST_FIELD_NUMBER = 2
  def of(
    requestUnion: com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest.RequestUnion
  ): _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest = _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.WatchRequest(
    requestUnion
  )
}
