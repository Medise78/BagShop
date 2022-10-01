package com.mahdi.snickersshop.ui.presentation.detail_screen


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mahdi.snickersshop.data.model.Product
import com.mahdi.snickersshop.ui.presentation.detail_screen.component.CategoryTab
import com.mahdi.snickersshop.ui.presentation.detail_screen.component.Images
import com.mahdi.snickersshop.ui.presentation.detail_screen.component.TopBar
import com.mahdi.snickersshop.util.CATEGORY
import com.mahdi.snickersshop.util.NetworkChecker
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun DetailScreen(
    navHostController: NavController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val state by detailViewModel.data.observeAsState()
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    detailViewModel.liveData.value = NetworkChecker(context).isNetworkConnected

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.89f)
                .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
            shape = RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    TopBar()
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Find your",
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier.padding(start = 22.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "match style!",
                        style = MaterialTheme.typography.body1,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(start = 22.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CategoryTab(
                        categories = CATEGORY.map { it.first },
                        selectedCategory = lazyListState.firstVisibleItemIndex.getCategory(
                            state ?: emptyList()
                        ),
                        onCategorySelected = { category ->
                            scope.launch { lazyListState.animateScrollToItem(category.getIndex(state?: emptyList())) }

                        })
                    state?.let { Images(navHostController, it,lazyListState) }
                }
            }
        }
    }
}

private fun Int.getCategory(menu: List<Product>): String {

    return CATEGORY.map { it.first }.last {
        it.getIndex(menu) <= this
    }
}

private fun String.getIndex(menu: List<Product>): Int {
    val category = CATEGORY.map { it.first }
    var index = 0
    for (i in 0 until category.indexOf(this)) {
        index += 1
        index += menu.filter { it.category == category[i] }.size
    }
    return index
}
