(ns client-side-routing.server
  (:require [clojure.tools.logging :as log]
            [client-side-routing.handler :refer [handler]]
            [client-side-routing.config :as config]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))


(defn -main [& args]
  (log/infof "Starting server on port %s" config/port)
  (run-jetty handler {:port config/port :join? false}))
