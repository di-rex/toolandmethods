(ns electroinv.db.core
  (:require [clojure.java.jdbc :as j]))

(def db       {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/electroinv"
               :user "root"
               :password ""})

(defn get-inventory [] ;gets all the components from the db
  (j/query db
    ["select * from components"]))

(defn get-component [id] ;gets a single component from the db
  (j/query db
    ["select * from components where id = ?" id]))

(defn set-component [name type value] ;creates a component / type0 - capacitor, type1 - resistor
  (j/insert! db :components
    {:name name
     :type type
     :value value}))

(defn patch-component [id & [name type value]] ;patches a component
  (j/update! db :components
    {:name name
     :type type
     :value value} ["id = ?" id]))

(defn delete-component [id] ;deletes a component
  (j/delete! db :components
    ["id = ?" id]))

(defn get-max [type] ;gets the maximal value of capacitance or resistance
  (apply merge-with +
    (j/query db
      ["select value from components where type = ?" type])))

(defn get-min [type] ;gets the minimal value of capacitance or resistance
  (j/query db
      ["select min(value) from components where type = ?" type]))

(defn get-avg [type] ;gets the average value of capacitance or resistance
  (j/query db
    ["select avg(value) from components where type = ?" type]))