(ns client-side-routing.subs
  (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 :subs/name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :subs/active-panel
 (fn [db _]
   (:active-panel db)))
