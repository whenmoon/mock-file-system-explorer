(ns mock-file-system.events
  (:require
   [re-frame.core :as re-frame]
   [mock-file-system.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 :set-nodes
 (fn [db [_ node]]
   (assoc db :nodes (conj (db :nodes) node))))