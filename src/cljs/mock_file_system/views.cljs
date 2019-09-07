(ns mock-file-system.views
  (:require
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]
   [mock-file-system.subs :as subs]
   [mock-file-system.node :as node]
   [mock-file-system.api-service :as api]))

(defn main-panel []
  (api/getFileTree)
  (reagent/create-class
   {:component-did-mount
    (fn [] (println "I mounted"))
    :reagent-render
    (fn []
      [node/node])}))
