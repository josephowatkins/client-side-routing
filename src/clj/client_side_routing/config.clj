(ns client-side-routing.config
  (:require [environ.core :refer [env]]))


(def port
  (Integer/parseInt (env :port "3000")))
