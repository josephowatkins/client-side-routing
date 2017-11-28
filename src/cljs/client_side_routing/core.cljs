(ns client-side-routing.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [client-side-routing.events :as events]
            [client-side-routing.routes :as routes]
            [client-side-routing.views :as views]
            [client-side-routing.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))


(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:events/initialize-db])
  (dev-setup)
  (mount-root))
