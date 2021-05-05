package mapreduce

import java.security.MessageDigest

object Md5 {
  val digest = MessageDigest.getInstance("MD5")

  def md5(str: String): String =
    digest.digest(str.getBytes).map("%02x".format(_)).mkString
}
