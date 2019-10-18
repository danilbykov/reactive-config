// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.github.fit51.reactiveconfig.etcd.gen.lock

object LockProto extends _root_.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.scalapb.GeneratedFileObject] = Seq(
    com.github.fit51.reactiveconfig.etcd.gen.rpc.RpcProto
  )
  lazy val messagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      com.github.fit51.reactiveconfig.etcd.gen.lock.LockRequest,
      com.github.fit51.reactiveconfig.etcd.gen.lock.LockResponse,
      com.github.fit51.reactiveconfig.etcd.gen.lock.UnlockRequest,
      com.github.fit51.reactiveconfig.etcd.gen.lock.UnlockResponse
    )
  private lazy val ProtoBytes: Array[Byte] =
      scalapb.Encoding.fromBase64(scala.collection.immutable.Seq(
  """Cgpsb2NrLnByb3RvEgh2M2xvY2twYhoJcnBjLnByb3RvIk4KC0xvY2tSZXF1ZXN0Eh0KBG5hbWUYASABKAxCCeI/BhIEbmFtZ
  VIEbmFtZRIgCgVsZWFzZRgCIAEoA0IK4j8HEgVsZWFzZVIFbGVhc2UibQoMTG9ja1Jlc3BvbnNlEkEKBmhlYWRlchgBIAEoCzIcL
  mV0Y2RzZXJ2ZXJwYi5SZXNwb25zZUhlYWRlckIL4j8IEgZoZWFkZXJSBmhlYWRlchIaCgNrZXkYAiABKAxCCOI/BRIDa2V5UgNrZ
  XkiKwoNVW5sb2NrUmVxdWVzdBIaCgNrZXkYASABKAxCCOI/BRIDa2V5UgNrZXkiUwoOVW5sb2NrUmVzcG9uc2USQQoGaGVhZGVyG
  AEgASgLMhwuZXRjZHNlcnZlcnBiLlJlc3BvbnNlSGVhZGVyQgviPwgSBmhlYWRlclIGaGVhZGVyMn4KBExvY2sSNwoETG9jaxIVL
  nYzbG9ja3BiLkxvY2tSZXF1ZXN0GhYudjNsb2NrcGIuTG9ja1Jlc3BvbnNlIgASPQoGVW5sb2NrEhcudjNsb2NrcGIuVW5sb2NrU
  mVxdWVzdBoYLnYzbG9ja3BiLlVubG9ja1Jlc3BvbnNlIgBCKgooY29tLmdpdGh1Yi5maXQ1MS5yZWFjdGl2ZWNvbmZpZy5ldGNkL
  mdlbmIGcHJvdG8z"""
      ).mkString)
  lazy val scalaDescriptor: _root_.scalapb.descriptors.FileDescriptor = {
    val scalaProto = com.google.protobuf.descriptor.FileDescriptorProto.parseFrom(ProtoBytes)
    _root_.scalapb.descriptors.FileDescriptor.buildFrom(scalaProto, dependencies.map(_.scalaDescriptor))
  }
  lazy val javaDescriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val javaProto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(ProtoBytes)
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(javaProto, Array(
      com.github.fit51.reactiveconfig.etcd.gen.rpc.RpcProto.javaDescriptor
    ))
  }
  @deprecated("Use javaDescriptor instead. In a future version this will refer to scalaDescriptor.", "ScalaPB 0.5.47")
  def descriptor: com.google.protobuf.Descriptors.FileDescriptor = javaDescriptor
}