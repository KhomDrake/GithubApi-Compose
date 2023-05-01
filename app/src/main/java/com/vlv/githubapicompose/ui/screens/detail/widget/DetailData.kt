package com.vlv.githubapicompose.ui.screens.detail.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vlv.githubapicompose.domain.data.Repository
import com.vlv.githubapicompose.ui.theme.Typography

@Composable
fun DetailData(repository: Repository) {
    val nameId = "name"
    val fullNameId = "full_name"
    val authorImageId = "author_image"
    val descriptionTitleId = "description_title"
    val descriptionTextId = "description_text"
    val forksId = "forks"
    val openIssuesId = "open_issues"
    val watchersId = "watchers"
    val starsId = "stars"

    val constraintSet = ConstraintSet {
        val name = createRefFor(nameId)
        val fullName = createRefFor(fullNameId)
        val authorImage = createRefFor(authorImageId)
        val descriptionTitle = createRefFor(descriptionTitleId)
        val descriptionText = createRefFor(descriptionTextId)
        val forks = createRefFor(forksId)
        val openIssues = createRefFor(openIssuesId)
        val watchers = createRefFor(watchersId)
        val stars = createRefFor(starsId)

        constrain(name) {
            top.linkTo(authorImage.top)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(authorImage.start, margin = 16.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(fullName) {
            top.linkTo(name.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(authorImage.start, margin = 16.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(authorImage) {
            top.linkTo(parent.top, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            width = Dimension.value(48.dp)
            height = Dimension.value(48.dp)
        }

        val barrier = createBottomBarrier(
            name, fullName, authorImage, margin = 16.dp
        )

        constrain(descriptionTitle) {
            top.linkTo(barrier)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(descriptionText) {
            top.linkTo(descriptionTitle.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        val guidelineMid = createGuidelineFromStart(.5f)

        constrain(forks) {
            top.linkTo(descriptionText.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(guidelineMid, margin = 8.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(openIssues) {
            top.linkTo(forks.bottom, margin = 4.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(guidelineMid, margin = 8.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(watchers) {
            top.linkTo(descriptionText.bottom, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            start.linkTo(guidelineMid, margin = 8.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(stars) {
            top.linkTo(watchers.bottom, margin = 4.dp)
            end.linkTo(parent.end, margin = 16.dp)
            start.linkTo(guidelineMid, margin = 8.dp)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

    }

    ConstraintLayout(constraintSet, modifier = Modifier.fillMaxSize()) {
        Text(
            text = repository.name,
            style = Typography.h1,
            modifier = Modifier.layoutId(nameId)
        )

        Text(
            text = repository.fullName,
            style = Typography.h2,
            modifier = Modifier.layoutId(fullNameId)
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(repository.url)
                .crossfade(true)
                .build(),
            contentDescription = "Owner Repository",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .layoutId(authorImageId)
        )

        Text(
            text = "Description:",
            style = Typography.h1,
            modifier = Modifier.layoutId(descriptionTitleId)
        )

        Text(
            text = repository.description,
            style = Typography.body1,
            modifier = Modifier.layoutId(descriptionTextId)
        )

        Text(
            text = "Forks: ${repository.forks}",
            style = Typography.body1,
            modifier = Modifier.layoutId(forksId)
        )

        Text(
            text = "Open Issues: ${repository.openIssues}",
            style = Typography.body1,
            modifier = Modifier.layoutId(openIssuesId)
        )

        Text(
            text = "Watchers: ${repository.watchers}",
            style = Typography.body1,
            modifier = Modifier.layoutId(watchersId)
        )

        Text(
            text = "Stars: ${repository.stars}",
            style = Typography.body1,
            modifier = Modifier.layoutId(starsId)
        )
    }

}