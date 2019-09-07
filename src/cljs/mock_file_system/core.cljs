(ns mock-file-system.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [mock-file-system.events :as events]
   [mock-file-system.views :as views]
   [mock-file-system.config :as config]
  ;  [clj-http.client :as http]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
