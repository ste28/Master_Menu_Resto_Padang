package com.example.mastermenurestopadang

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment

class UserFragment : Fragment() {
    lateinit var userListView: ListView
    lateinit var userListViewAdapter:ListUserAdapter
    var users: ArrayList<User>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        users = arguments?.getParcelableArrayList("listUser")

        if (users != null) {
            userListView = view.findViewById(R.id.listUser_lv)
            userListViewAdapter = ListUserAdapter(view.context, R.layout.list_user_item, users!!)
            userListView.adapter = userListViewAdapter
        } else {

        }
        registerForContextMenu(userListView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        activity?.menuInflater?.inflate(R.menu.contex_delete_user_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info =item.menuInfo as AdapterView.AdapterContextMenuInfo
        if (info.position >= 0) {
            val index = info.position
            users?.removeAt(index)
            userListViewAdapter.notifyDataSetChanged()
            val bundle = Bundle()
            bundle.putParcelableArrayList("listUser", users)
            val intent = Intent(view?.context, MainActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        return true
    }
}