(ns mock-file-system.api-service

  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [re-frame.core :as re-frame]
   [mock-file-system.subs :as subs]
   [cljs-http.client :as http]
   [cljs.core.async :refer [<!]]))

(def API-URL "https://private-5cbaa3-filetree.apiary-mock.com/file-tree")
(def request-opts {:with-credentials? false})

; (defn is-folder [node-type]
;   (if (node-type))
;   )

; (defn dispatch-nodes-to-state [node & {:keys [depth] :or {depth 0}}]
;   (re-frame/dispatch [:set-nodes (conj {:depth depth} node)])
;   (if (and (>= (count (node :children)) 1) (= (node :type) "folder"))
;     (recur (node :children) (inc depth))
; ; (println "CHILDREN >>>>>>>>>>>>" (node :children))
;     (println "it's a file")))

(defn dispatch-nodes-to-state [nodes & {:keys [depth] :or {depth 0}}]
  (doseq [node nodes]
    (if (= (node :type) "folder")
      (do
        ; (prn node)
        (re-frame/dispatch [:set-nodes (conj {:depth depth} node)])
        (dispatch-nodes-to-state (node :children) :depth (inc depth)))

      (do
        (re-frame/dispatch [:set-nodes (conj {:depth depth} node)])
        (dispatch-nodes-to-state (node :children) :depth (inc depth))
        ))))

; (defn dispatch-nodes-to-state [nodes & {:keys [depth] :or {depth 0}}]
;   (doseq [node nodes]
;     (re-frame/dispatch [:set-nodes (conj {:depth depth} node)])
;     (if (= (node :type) "folder")
;       (do
;         (println "It's a folder")
;         (dispatch-nodes-to-state (node :children) :depth (inc depth)))
;       (println "it's a file"))))

(defn traverse-tree [nodes]
  (run! dispatch-nodes-to-state nodes))

; (defn get-file-tree []
;   (go (let [file-tree (<! (http/get API-URL request-opts))]
;         (traverse-tree (get-in file-tree [:body :root])))))

(defn get-file-tree []
  (go (let [file-tree (<! (http/get API-URL request-opts))]
        (dispatch-nodes-to-state (get-in file-tree [:body :root])))))

; (println "it's a folder")