package com.movile.jvmserver

import com.ericsson.otp.erlang.OtpErlangPid
import com.ericsson.otp.erlang.OtpErlangString
import com.ericsson.otp.erlang.OtpErlangTuple
import com.ericsson.otp.erlang.OtpNode

fun main(args: Array<String>) {

    // beam vm node
    val node = OtpNode("jvm-server", "63ee2343-662d-4679-965c-356f86b18aef", 9001)

    // otp process
    val mbox = node.createMbox("jvm-process")

    while (true) {
        println("Waiting for OTP message...")
        val tuple = mbox.receive() as OtpErlangTuple
        val lastPid = tuple.elementAt(0) as OtpErlangPid
        println("Message from pid ${lastPid.id()}")
        mbox.send(lastPid, OtpErlangString("olá java"))
    }
}