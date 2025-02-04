package mx.tejate.contactskmp.config

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import mx.tejate.contactskmp.di.KoinAndroidModule

actual class DatabaseDriver {

    actual fun build(
        databaseName: String,
        schema: SqlSchema<QueryResult.Value<Unit>>
    ): SqlDriver {
        return AndroidSqliteDriver(
            schema = schema,
            context = KoinAndroidModule().context(),
            name = databaseName
        )
    }

}