// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.fit51.reactiveconfig.etcd.gen.rpc

@SerialVersionUID(0L)
final case class ResponseOp(
    response: com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.Empty
    ) extends scalapb.GeneratedMessage with scalapb.Message[ResponseOp] with scalapb.lenses.Updatable[ResponseOp] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      if (response.responseRange.isDefined) {
        val __value = response.responseRange.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (response.responsePut.isDefined) {
        val __value = response.responsePut.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (response.responseDeleteRange.isDefined) {
        val __value = response.responseDeleteRange.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (response.responseTxn.isDefined) {
        val __value = response.responseTxn.get
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
      response.responseRange.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      response.responsePut.foreach { __v =>
        val __m = __v
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      response.responseDeleteRange.foreach { __v =>
        val __m = __v
        _output__.writeTag(3, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      response.responseTxn.foreach { __v =>
        val __m = __v
        _output__.writeTag(4, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp = {
      var __response = this.response
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseRange(_root_.scalapb.LiteParser.readMessage(_input__, response.responseRange.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse.defaultInstance)))
          case 18 =>
            __response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponsePut(_root_.scalapb.LiteParser.readMessage(_input__, response.responsePut.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse.defaultInstance)))
          case 26 =>
            __response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseDeleteRange(_root_.scalapb.LiteParser.readMessage(_input__, response.responseDeleteRange.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse.defaultInstance)))
          case 34 =>
            __response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseTxn(_root_.scalapb.LiteParser.readMessage(_input__, response.responseTxn.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse.defaultInstance)))
          case tag => _input__.skipField(tag)
        }
      }
      com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp(
          response = __response
      )
    }
    def getResponseRange: com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse = response.responseRange.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse.defaultInstance)
    def withResponseRange(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse): ResponseOp = copy(response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseRange(__v))
    def getResponsePut: com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse = response.responsePut.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse.defaultInstance)
    def withResponsePut(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse): ResponseOp = copy(response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponsePut(__v))
    def getResponseDeleteRange: com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse = response.responseDeleteRange.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse.defaultInstance)
    def withResponseDeleteRange(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse): ResponseOp = copy(response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseDeleteRange(__v))
    def getResponseTxn: com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse = response.responseTxn.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse.defaultInstance)
    def withResponseTxn(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse): ResponseOp = copy(response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseTxn(__v))
    def clearResponse: ResponseOp = copy(response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.Empty)
    def withResponse(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response): ResponseOp = copy(response = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => response.responseRange.orNull
        case 2 => response.responsePut.orNull
        case 3 => response.responseDeleteRange.orNull
        case 4 => response.responseTxn.orNull
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => response.responseRange.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => response.responsePut.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 3 => response.responseDeleteRange.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 4 => response.responseTxn.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp
}

object ResponseOp extends scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, _root_.scala.Any]): com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp = {
    _root_.scala.Predef.require(__fieldsMap.keys.forall(_.getContainingType() == javaDescriptor), "FieldDescriptor does not match message type.")
    val __fields = javaDescriptor.getFields
    com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp(
      response = __fieldsMap.get(__fields.get(0)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse]].map(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseRange)
    .orElse[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response](__fieldsMap.get(__fields.get(1)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse]].map(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponsePut))
    .orElse[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response](__fieldsMap.get(__fields.get(2)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse]].map(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseDeleteRange))
    .orElse[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response](__fieldsMap.get(__fields.get(3)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse]].map(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseTxn))
    .getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.Empty)
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage == scalaDescriptor), "FieldDescriptor does not match message type.")
      com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp(
        response = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse]]).map(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseRange)
    .orElse[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response](__fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse]]).map(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponsePut))
    .orElse[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response](__fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse]]).map(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseDeleteRange))
    .orElse[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response](__fieldsMap.get(scalaDescriptor.findFieldByNumber(4).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse]]).map(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseTxn))
    .getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.Empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = RpcProto.javaDescriptor.getMessageTypes.get(8)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = RpcProto.scalaDescriptor.messages(8)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse
      case 2 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse
      case 3 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse
      case 4 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp(
    response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.Empty
  )
  sealed trait Response extends _root_.scalapb.GeneratedOneof {
    def isEmpty: _root_.scala.Boolean = false
    def isDefined: _root_.scala.Boolean = true
    def isResponseRange: _root_.scala.Boolean = false
    def isResponsePut: _root_.scala.Boolean = false
    def isResponseDeleteRange: _root_.scala.Boolean = false
    def isResponseTxn: _root_.scala.Boolean = false
    def responseRange: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse] = _root_.scala.None
    def responsePut: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse] = _root_.scala.None
    def responseDeleteRange: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse] = _root_.scala.None
    def responseTxn: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse] = _root_.scala.None
  }
  object Response extends {
    @SerialVersionUID(0L)
    case object Empty extends com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response {
      type ValueType = _root_.scala.Nothing
      override def isEmpty: _root_.scala.Boolean = true
      override def isDefined: _root_.scala.Boolean = false
      override def number: _root_.scala.Int = 0
      override def value: _root_.scala.Nothing = throw new java.util.NoSuchElementException("Empty.value")
    }
  
    @SerialVersionUID(0L)
    final case class ResponseRange(value: com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse) extends com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response {
      type ValueType = com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse
      override def isResponseRange: _root_.scala.Boolean = true
      override def responseRange: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse] = Some(value)
      override def number: _root_.scala.Int = 1
    }
    @SerialVersionUID(0L)
    final case class ResponsePut(value: com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse) extends com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response {
      type ValueType = com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse
      override def isResponsePut: _root_.scala.Boolean = true
      override def responsePut: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse] = Some(value)
      override def number: _root_.scala.Int = 2
    }
    @SerialVersionUID(0L)
    final case class ResponseDeleteRange(value: com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse) extends com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response {
      type ValueType = com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse
      override def isResponseDeleteRange: _root_.scala.Boolean = true
      override def responseDeleteRange: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse] = Some(value)
      override def number: _root_.scala.Int = 3
    }
    @SerialVersionUID(0L)
    final case class ResponseTxn(value: com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse) extends com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response {
      type ValueType = com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse
      override def isResponseTxn: _root_.scala.Boolean = true
      override def responseTxn: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse] = Some(value)
      override def number: _root_.scala.Int = 4
    }
  }
  implicit class ResponseOpLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp](_l) {
    def responseRange: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.RangeResponse] = field(_.getResponseRange)((c_, f_) => c_.copy(response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseRange(f_)))
    def responsePut: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.PutResponse] = field(_.getResponsePut)((c_, f_) => c_.copy(response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponsePut(f_)))
    def responseDeleteRange: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.DeleteRangeResponse] = field(_.getResponseDeleteRange)((c_, f_) => c_.copy(response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseDeleteRange(f_)))
    def responseTxn: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.TxnResponse] = field(_.getResponseTxn)((c_, f_) => c_.copy(response = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response.ResponseTxn(f_)))
    def response: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response] = field(_.response)((c_, f_) => c_.copy(response = f_))
  }
  final val RESPONSE_RANGE_FIELD_NUMBER = 1
  final val RESPONSE_PUT_FIELD_NUMBER = 2
  final val RESPONSE_DELETE_RANGE_FIELD_NUMBER = 3
  final val RESPONSE_TXN_FIELD_NUMBER = 4
  def of(
    response: com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp.Response
  ): _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp = _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseOp(
    response
  )
}
