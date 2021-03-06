package com.arcusys.valamis.lrs.liferay.history.ver250

import com.arcusys.valamis.lrs.DbUpgrade
import com.arcusys.valamis.lrs.liferay.history.SQLRunner
import com.arcusys.valamis.lrs.liferay.{Loggable, UpgradeProcess}
import com.google.inject.Key
import com.google.inject.name.Names

class DbUpgradeProcess extends UpgradeProcess with SQLRunner with Loggable {
  override def getThreshold = 250
  val schema = injector.getInstance(Key.get(classOf[DbUpgrade], Names.named("ver250")))

  override def doUpgrade() {
    schema.up(lrs)
  }
}

