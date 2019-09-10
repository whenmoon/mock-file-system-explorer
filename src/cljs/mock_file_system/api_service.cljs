(ns mock-file-system.api-service

  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [re-frame.core :as re-frame]
   [mock-file-system.subs :as subs]
   [cljs-http.client :as http]
   [cljs.core.async :refer [<!]]))

(def API-URL "https://private-5cbaa3-filetree.apiary-mock.com/file-tree")
(def request-opts {:with-credentials? false})

(defn dispatch-nodes-to-state [node]
  (re-frame/dispatch [:set-api-data node])
  )

(defn traverse-tree [nodes]
  (map dispatch-nodes-to-state nodes)
  )

(defn get-file-tree []
  (go (let [file-tree (<! (http/get API-URL request-opts))]
        (println (first(get-in file-tree [:body :root])))
        ; (traverse-tree [(first (get-in file-tree [:body :root]))])
        )))
