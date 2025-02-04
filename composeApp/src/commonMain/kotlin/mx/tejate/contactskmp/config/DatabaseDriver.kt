package mx.tejate.contactskmp.config

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema

expect class DatabaseDriver() {
    fun build(
        databaseName: String,
        schema: SqlSchema<QueryResult.Value<Unit>>
    ): SqlDriver
}