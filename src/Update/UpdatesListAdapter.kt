package top.easterNday.settings.Update


/*
 * Copyright (C) 2017-2023 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import top.easterNday.settings.DogDay.Utils.Companion.downloadFromUrl
import top.easterNday.settings.R


class UpdatesListAdapter(private val dataSet: ArrayList<UpdateItem>) :
    RecyclerView.Adapter<UpdatesListAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val version: TextView
        val datetime: TextView
        val size: TextView
        val tag: TextView
        val desc: TextView
        val downloadButton: Button
        val mMenu = view.findViewById<ImageButton>(R.id.updateMenu)

        init {
            // Define click listener for the ViewHolder's View.
            title = view.findViewById(R.id.updateTitle)
            version = view.findViewById(R.id.updateVersion)
            datetime = view.findViewById(R.id.updateDate)
            size = view.findViewById(R.id.updateSize)
            tag = view.findViewById(R.id.updateTag)
            desc = view.findViewById(R.id.updateDesc)
            downloadButton = view.findViewById(R.id.updateAction)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.update_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.text = dataSet[position].updateTitle
        viewHolder.version.text = dataSet[position].updateVersion
        viewHolder.datetime.text = dataSet[position].updateDate
        viewHolder.size.text = dataSet[position].updateSize
        viewHolder.desc.text = dataSet[position].updateDesc
        viewHolder.tag.text = dataSet[position].updateTag

        // 点击下载按钮开始下载
        viewHolder.downloadButton.setOnClickListener {
            // 打开网页
            // val intent = Intent(Intent.ACTION_VIEW)
            // intent.data = Uri.parse(dataSet[position].updateUrl)
            // viewHolder.itemView.context.startActivity(intent)

            // 调用系统服务进行下载
            downloadFromUrl(viewHolder.itemView.context,dataSet[position].updateUrl,dataSet[position].updateTitle,dataSet[position].updateDesc,".ROMs")
        }

//        viewHolder.downloadButton.setOnClickListener{
//            val downloadsDir = File("/storage/emulated/0/Download")
//            val file = File(downloadsDir, "1.zip")
//            Log.d("TongInstall", file.name)
//            RecoverySystem.installPackage(viewHolder.itemView.context,file)
//        }
    }

    fun addUpdateItem(item: UpdateItem) {
        // 异步添加item的逻辑
        dataSet.add(item)
        // 添加完成后调用notifyItemInserted()方法
        notifyItemInserted(dataSet.size - 1) // 获取新添加item的位置
    }
}