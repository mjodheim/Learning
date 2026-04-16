using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace EFCore.Migrations
{
    /// <inheritdoc />
    public partial class AddGarages : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Garages",
                columns: table => new
                {
                    id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    name = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    address = table.Column<string>(type: "nvarchar(max)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Garages", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "Owners",
                columns: table => new
                {
                    id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    name = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    email = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Owners", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "Autos",
                columns: table => new
                {
                    d = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    brand = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    model = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    plate = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    owner_id = table.Column<int>(type: "int", nullable: false),
                    garage_id = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Autos", x => x.d);
                    table.ForeignKey(
                        name: "FK_Autos_Garages_garage_id",
                        column: x => x.garage_id,
                        principalTable: "Garages",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Autos_Owners_owner_id",
                        column: x => x.owner_id,
                        principalTable: "Owners",
                        principalColumn: "id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Autos_garage_id",
                table: "Autos",
                column: "garage_id");

            migrationBuilder.CreateIndex(
                name: "IX_Autos_owner_id",
                table: "Autos",
                column: "owner_id");

            migrationBuilder.CreateIndex(
                name: "IX_Autos_plate",
                table: "Autos",
                column: "plate",
                unique: true);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Autos");

            migrationBuilder.DropTable(
                name: "Garages");

            migrationBuilder.DropTable(
                name: "Owners");
        }
    }
}
