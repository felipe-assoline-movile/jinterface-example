package com.movile.jvmserver

import com.ericsson.otp.erlang.OtpErlangPid
import com.ericsson.otp.erlang.OtpErlangString
import com.ericsson.otp.erlang.OtpErlangTuple
import com.ericsson.otp.erlang.OtpNode

fun main(args: Array<String>) {

    // beam vm node
    val myOtpNode = OtpNode("jvm-server", "63ee2343-662d-4679-965c-356f86b18aef", 9001)

    // otp process
    val myOtpMbox = myOtpNode.createMbox("jvm-process")

    while (true) {
        try {
            println("Waiting for OTP message...")
            val tuple = myOtpMbox.receive() as OtpErlangTuple
            val lastPid = tuple.elementAt(0) as OtpErlangPid
            println("Message from pid ${lastPid.id()}")
            myOtpMbox.send(lastPid, OtpErlangString("olá java"))
            
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}