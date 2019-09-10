(ns mock-file-system.views
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]
   [mock-file-system.subs :as subs]
   [mock-file-system.node :as node]
   [mock-file-system.api-service :as api-service]
   [cljs.core.async :refer [<!]]))

(defn main-panel []

(def nodes (-> @(re-frame/subscribe [::subs/nodes]) :body :root))
; (println "Nodes in view.cljs >>>>>>>>>>>" nodes)  
  (reagent/create-class
   {:component-did-mount

    (fn []
      (api-service/get-file-tree))

    :reagent-render
    (fn []
      [node/node])
    
    }))
