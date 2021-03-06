package com.arcusys.learn.liferay.lrs.services

import com.liferay.portal.kernel.security.auth.CompanyThreadLocal

object CompanyHelper {
  def setCompanyId(companyId: Long): Unit = CompanyThreadLocal.setCompanyId(companyId)

  def getCompanyId: Long = CompanyThreadLocal.getCompanyId

}
