(ns client-side-routing.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:require [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [re-frame.core :as re-frame])
  (:import (goog.history Html5History)))

(defonce history
  (doto (Html5History.)
    (.setPathPrefix js/window.location.protocol "//" js/window.location.host)
    (.setUseFragment false)))

(defn get-token []
  (str js/window.location.pathname js/window.location.search))

(defn handle-url-change [e]
  (when-not (.-isNavigation e)
    (js/window.scrollTo 0 0))
  (secretary/dispatch! (get-token)))

(defn hook-browser-navigation! []
  (doto history
    (events/listen EventType/NAVIGATE handle-url-change)
    (.setEnabled true)))

(defn app-routes []
  (defroute home "/" []
    (re-frame/dispatch [:events/set-active-panel :home-panel]))

  (defroute about "/about" []
    (re-frame/dispatch [:events/set-active-panel :about-panel]))

  (defroute not-found "*" []
    (re-frame/dispatch [:events/set-active-panel]))

  (hook-browser-navigation!))

(defn nav! [path]
  (.setToken history path))
