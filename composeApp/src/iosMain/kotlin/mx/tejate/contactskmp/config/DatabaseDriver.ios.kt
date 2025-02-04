package mx.tejate.contactskmp.config

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DatabaseDriver {

    actual fun build(
        databaseName: String,
        schema: SqlSchema<QueryResult.Value<Unit>>
    ): SqlDriver {
        return NativeSqliteDriver(
            schema = schema,
            name = databaseName
        )
    }

}