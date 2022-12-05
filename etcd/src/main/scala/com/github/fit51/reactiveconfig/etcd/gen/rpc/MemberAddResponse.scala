// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.fit51.reactiveconfig.etcd.gen.rpc

/** @param member
  *   member is the member information for the added member.
  * @param members
  *   members is a list of all members after adding the new member.
  */
@SerialVersionUID(0L)
final case class MemberAddResponse(
    header: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] = _root_.scala.None,
    member: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member] = _root_.scala.None,
    members: _root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member] = _root_.scala.Seq.empty
    ) extends scalapb.GeneratedMessage with scalapb.Message[MemberAddResponse] with scalapb.lenses.Updatable[MemberAddResponse] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      if (header.isDefined) {
        val __value = header.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      if (member.isDefined) {
        val __value = member.get
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      members.foreach { __item =>
        val __value = __item
        __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }
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
      header.foreach { __v =>
        val __m = __v
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      member.foreach { __v =>
        val __m = __v
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      members.foreach { __v =>
        val __m = __v
        _output__.writeTag(3, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse = {
      var __header = this.header
      var __member = this.member
      val __members = (_root_.scala.collection.immutable.Vector.newBuilder[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member] ++= this.members)
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __header = Option(_root_.scalapb.LiteParser.readMessage(_input__, __header.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader.defaultInstance)))
          case 18 =>
            __member = Option(_root_.scalapb.LiteParser.readMessage(_input__, __member.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.Member.defaultInstance)))
          case 26 =>
            __members += _root_.scalapb.LiteParser.readMessage(_input__, com.github.fit51.reactiveconfig.etcd.gen.rpc.Member.defaultInstance)
          case tag => _input__.skipField(tag)
        }
      }
      com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse(
          header = __header,
          member = __member,
          members = __members.result()
      )
    }
    def getHeader: com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader = header.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader.defaultInstance)
    def clearHeader: MemberAddResponse = copy(header = _root_.scala.None)
    def withHeader(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader): MemberAddResponse = copy(header = Option(__v))
    def getMember: com.github.fit51.reactiveconfig.etcd.gen.rpc.Member = member.getOrElse(com.github.fit51.reactiveconfig.etcd.gen.rpc.Member.defaultInstance)
    def clearMember: MemberAddResponse = copy(member = _root_.scala.None)
    def withMember(__v: com.github.fit51.reactiveconfig.etcd.gen.rpc.Member): MemberAddResponse = copy(member = Option(__v))
    def clearMembers = copy(members = _root_.scala.Seq.empty)
    def addMembers(__vs: com.github.fit51.reactiveconfig.etcd.gen.rpc.Member*): MemberAddResponse = addAllMembers(__vs)
    def addAllMembers(__vs: Iterable[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member]): MemberAddResponse = copy(members = members ++ __vs)
    def withMembers(__v: _root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member]): MemberAddResponse = copy(members = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => header.orNull
        case 2 => member.orNull
        case 3 => members
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => header.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 2 => member.map(_.toPMessage).getOrElse(_root_.scalapb.descriptors.PEmpty)
        case 3 => _root_.scalapb.descriptors.PRepeated(members.iterator.map(_.toPMessage).toVector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse
}

object MemberAddResponse extends scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, _root_.scala.Any]): com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse = {
    _root_.scala.Predef.require(__fieldsMap.keys.forall(_.getContainingType() == javaDescriptor), "FieldDescriptor does not match message type.")
    val __fields = javaDescriptor.getFields
    com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse(
      __fieldsMap.get(__fields.get(0)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader]],
      __fieldsMap.get(__fields.get(1)).asInstanceOf[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member]],
      __fieldsMap.getOrElse(__fields.get(2), Nil).asInstanceOf[_root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member]]
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage == scalaDescriptor), "FieldDescriptor does not match message type.")
      com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse(
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader]]),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).flatMap(_.as[_root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member]]),
        __fieldsMap.get(scalaDescriptor.findFieldByNumber(3).get).map(_.as[_root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member]]).getOrElse(_root_.scala.Seq.empty)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = RpcProto.javaDescriptor.getMessageTypes.get(32)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = RpcProto.scalaDescriptor.messages(32)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = {
    var __out: _root_.scalapb.GeneratedMessageCompanion[_] = null
    (__number: @_root_.scala.unchecked) match {
      case 1 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader
      case 2 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.Member
      case 3 => __out = com.github.fit51.reactiveconfig.etcd.gen.rpc.Member
    }
    __out
  }
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse(
    header = _root_.scala.None,
    member = _root_.scala.None,
    members = _root_.scala.Seq.empty
  )
  implicit class MemberAddResponseLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse](_l) {
    def header: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader] = field(_.getHeader)((c_, f_) => c_.copy(header = Option(f_)))
    def optionalHeader: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader]] = field(_.header)((c_, f_) => c_.copy(header = f_))
    def member: _root_.scalapb.lenses.Lens[UpperPB, com.github.fit51.reactiveconfig.etcd.gen.rpc.Member] = field(_.getMember)((c_, f_) => c_.copy(member = Option(f_)))
    def optionalMember: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member]] = field(_.member)((c_, f_) => c_.copy(member = f_))
    def members: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member]] = field(_.members)((c_, f_) => c_.copy(members = f_))
  }
  final val HEADER_FIELD_NUMBER = 1
  final val MEMBER_FIELD_NUMBER = 2
  final val MEMBERS_FIELD_NUMBER = 3
  def of(
    header: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.ResponseHeader],
    member: _root_.scala.Option[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member],
    members: _root_.scala.Seq[com.github.fit51.reactiveconfig.etcd.gen.rpc.Member]
  ): _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse = _root_.com.github.fit51.reactiveconfig.etcd.gen.rpc.MemberAddResponse(
    header,
    member,
    members
  )
}
