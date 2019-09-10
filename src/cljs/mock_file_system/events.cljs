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
 :set-api-data
 (fn [db [_ file-tree]]
   (assoc db :nodes file-tree)))
