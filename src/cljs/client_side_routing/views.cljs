(ns client-side-routing.views
  (:require [re-frame.core :as re-frame]
            [client-side-routing.subs :as subs]
            [client-side-routing.routes :as routes]))


;; home

(defn home-panel []
  (let [name (re-frame/subscribe [:subs/name])]
    [:div (str "Hello from " @name ". This is the Home Page.")
     [:div [:a {:href (routes/about)
                :on-click #(do
                             (.preventDefault %)
                             (routes/nav! (routes/about)))}
            "go to About Page"]]]))


;; about

(defn about-panel []
  [:div "This is the About Page."
   [:div [:a {:href (routes/home)
              :on-click #(do
                           (.preventDefault %)
                           (routes/nav! (routes/home)))}
          "go to Home Page"]]])


;; not-found

(defn not-found-panel []
  [:div "Oops - this is not the page you are looking for..."
   [:div [:a {:href (routes/home)
              :on-click #(do
                           (.preventDefault %)
                           (routes/nav! (routes/home)))}
          "go to the Home Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [not-found-panel]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:subs/active-panel])]
    [show-panel @active-panel]))
