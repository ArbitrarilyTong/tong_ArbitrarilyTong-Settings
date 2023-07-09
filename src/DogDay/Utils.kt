package top.easterNday.settings.DogDay

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class Utils {

//    private fun InstallApk(context: Context, uri: Uri) {
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        intent.setDataAndType(uri, "application/vnd.android.package-archive")
//        context.startActivity(intent)
//    }

    companion object {
        fun toDate(timestamp: Double): String {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp.toLong() * 60), ZoneId.systemDefault())
                    .withSecond(0) // 去除秒数
            return localDateTime.format(formatter)
        }

        fun downloadFromUrl(context: Context, downloadUrl: String, title: String, desc: String, subdir: String): Long {
            val request = DownloadManager.Request(Uri.parse(downloadUrl))
            // 设置在什么网络情况下进行下载
            // request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
            //设置通知栏标题
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            request.setTitle(title)
            request.setDescription(desc)
            //request.setAllowedOverRoaming(false);
            //设置文件存放目录
            request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, subdir)


            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            return downloadManager.enqueue(request)
        }
    }
}