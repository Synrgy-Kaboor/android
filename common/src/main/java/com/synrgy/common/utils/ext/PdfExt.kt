package com.synrgy.common.utils.ext

import android.content.Context
import android.content.Intent
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.InputStream
import java.util.UUID


/**
 * Created by wahid on 2/12/2024.
 * Github github.com/wahidabd.
 */


fun AppCompatActivity.openPDFContent(
    id: Int,
    inputStream: InputStream,
    result: (Intent) -> Unit
) {
    val file = inputStream.saveToFile("$id", this)
    val uri = FileProvider.getUriForFile(
        this,
        "${this.packageName}.provider",
        file
    )
    val intent = Intent(Intent.ACTION_VIEW).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        setDataAndType(uri, "application/pdf")
    }
    result.invoke(intent)
}

private fun InputStream.saveToFile(
    fileName: String,
    context: Context
): File =
    use { input ->
        val file = File.createTempFile(
            "Kaboor_$fileName",
            ".pdf",
            context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        )
        file.outputStream().use { output ->
            input.copyTo(output)
        }
        input.close()
        file
    }