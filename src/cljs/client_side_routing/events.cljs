(ns client-side-routing.events
  (:require [re-frame.core :as re-frame]
            [client-side-routing.db :as db]))


(re-frame/reg-event-db
 :events/initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :events/set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
