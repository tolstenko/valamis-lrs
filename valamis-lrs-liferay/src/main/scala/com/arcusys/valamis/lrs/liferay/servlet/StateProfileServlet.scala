package com.arcusys.valamis.lrs.liferay.servlet

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import com.arcusys.valamis.lrs.liferay.exception.{InvalidOrMissingArgumentException, NotFoundException}
import com.arcusys.valamis.lrs.liferay.servlet
import com.arcusys.valamis.lrs.liferay.servlet.request.TincanActivityStateRequest
import com.google.inject.{Inject, Injector, Singleton}

@Singleton
class StateProfileServlet @Inject()(inj: Injector) extends BaseLrsServlet(inj) {

  override def doGet(request: HttpServletRequest,
                     response: HttpServletResponse): Unit = jsonAction[TincanActivityStateRequest](model => {

    model.stateId match {
      case Some(stateId) =>
        lrs.getStateProfile(
          model.agent,
          model.activityId,
          stateId,
          model.registration) match {

          case Some(state) => state.contents
          case None => throw new NotFoundException("State profile")
        }
      case None =>
        lrs.getStateProfiles(
          model.agent,
          model.activityId,
          model.registration,
          model.since)
    }
  }, request, response)

  override def doPost(request: HttpServletRequest,
                      response: HttpServletResponse): Unit = jsonAction[TincanActivityStateRequest](model => {
    model.stateId match {
      case Some(stateId) => lrs.addOrUpdateStateProfile(model.agent,
        model.activityId,
        stateId,
        model.registration,
        model.document)
      case None => throw new InvalidOrMissingArgumentException("stateId")
    }
    Unit
  }, request, response)



  override def doPut(request: HttpServletRequest,
                     response: HttpServletResponse): Unit = jsonAction[TincanActivityStateRequest](model => {

    model.stateId match {
      case Some(stateId) => lrs.addOrUpdateStateProfile(model.agent,
        model.activityId,
        stateId,
        model.registration,
        model.document)
      case None => throw new InvalidOrMissingArgumentException("stateId")
    }
    Unit
  }, request, response)

  override def doDelete(request: HttpServletRequest,
                        response: HttpServletResponse): Unit = jsonAction[TincanActivityStateRequest](model => {

    model.stateId match {
      case Some(stateId) =>
        lrs.deleteStateProfile(
          model.agent,
          model.activityId,
          stateId,
          model.registration)
      case None =>
        lrs.deleteStateProfiles(
          model.agent,
          model.activityId,
          model.registration)
    }
    Unit
  }, request, response)

  override val ServletName: String = "StateProfile"
}
