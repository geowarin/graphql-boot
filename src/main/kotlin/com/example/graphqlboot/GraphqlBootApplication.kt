package com.example.graphqlboot

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.zeroturnaround.exec.ProcessExecutor
import org.zeroturnaround.exec.StartedProcess
import org.zeroturnaround.exec.stream.slf4j.Slf4jStream
import java.io.File

@SpringBootApplication
class GraphqlBootApplication

fun yarnStart(): StartedProcess {
  return ProcessExecutor()
    .command("yarn", "start")
    .directory(File("./src/main/ts"))
    .redirectOutput(Slf4jStream.of(LoggerFactory.getLogger("yarn")).asInfo())
    .redirectError(Slf4jStream.of(LoggerFactory.getLogger("yarn")).asError()).start()
}

fun main(args: Array<String>) {
  if (!isRestart()) {
    yarnStart()
  }
  SpringApplication.run(GraphqlBootApplication::class.java, *args)
}

private fun isRestart() = Thread.currentThread().name == "restartedMain"
