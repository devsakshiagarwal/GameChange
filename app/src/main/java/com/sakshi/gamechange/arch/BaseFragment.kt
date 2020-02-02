package com.sakshi.gamechange.arch

import androidx.fragment.app.Fragment
import com.sakshi.gamechange.GameChange

open class BaseFragment : Fragment() {
  private var compRoot: CompRootUi? = null

  protected fun compRoot(): CompRootUi? {
    if (compRoot == null) {
      compRoot = CompRootUi(
          (this.activity!!.application as GameChange).getCompRoot(),
          this.activity!!
      )
    }
    return compRoot
  }
}