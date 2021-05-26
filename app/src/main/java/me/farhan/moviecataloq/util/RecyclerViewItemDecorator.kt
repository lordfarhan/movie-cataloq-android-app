package me.farhan.moviecataloq.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


/**
 * @author farhan
 * created at at 13:48 on 01/05/21.
 */
class RecyclerViewItemDecorator(private val edgePadding: Int) : ItemDecoration() {
  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    super.getItemOffsets(outRect, view, parent, state)
    val itemCount = state.itemCount
    val itemPosition = parent.getChildAdapterPosition(view)

    // no position, leave it alone
    if (itemPosition == RecyclerView.NO_POSITION) {
      return
    }

    // first item
    if (itemPosition == 0) {
      outRect.set(
        edgePadding,
        view.paddingTop,
        view.paddingRight,
        view.paddingBottom
      )
    } else if (itemCount > 0 && itemPosition == itemCount - 1) {
      outRect.set(
        view.paddingLeft,
        view.paddingTop,
        edgePadding,
        view.paddingBottom
      )
    } else {
      outRect.set(
        view.paddingLeft,
        view.paddingTop,
        view.paddingRight,
        view.paddingBottom
      )
    }
  }

}