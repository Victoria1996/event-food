USE [event-food]

SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[Role] (
  [id_role]   [int]          NOT NULL,
  [role_name] [nvarchar](50) NULL,
  CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED
    (
      [id_role] ASC
    )
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
) ON [PRIMARY]

CREATE TABLE [dbo].[Client] (
  [id_client]         [int]           NOT NULL,
  [email]             [nvarchar](70)  NULL,
  [registration_date] [date]          NULL,
  [client_name]       [nvarchar](80)  NULL,
  [id_role]           [int]           NOT NULL,
  [password]          [nvarchar](150) NULL,
  [created]           [date]          NULL,
  [modified]          [date]          NULL,
  [deleted]           [date]          NULL,
  CONSTRAINT [PK_Clients] PRIMARY KEY CLUSTERED
    (
      [id_client] ASC
    )
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
) ON [PRIMARY]

CREATE TABLE [dbo].[Place] (
  [id_place]          [int]           NOT NULL,
  [address]           [nvarchar](250) NULL,
  [place_number]      [int]           NULL,
  [rating]            [numeric](3, 2) NULL,
  [id_client]         [int]           NULL,
  [place_name]        [nvarchar](100) NULL,
  [place_description] [nvarchar](500) NULL,
  [types_of_tables] [nvarchar](max) NULL,
  [working_hours] [nvarchar](max) NULL,
  CONSTRAINT [PK_Places] PRIMARY KEY CLUSTERED
    (
      [id_place] ASC
    )
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
) ON [PRIMARY]

CREATE TABLE [dbo].[Event] (
  [id_event]                 [int]           NOT NULL,
  [start_date]               [date]          NULL,
  [finish_date]              [date]          NULL,
  [cancel_registration_date] [date]          NULL,
  [status]                   [nvarchar](50)  NULL,
  [id_place]                 [int]           NULL,
  [event_number]             [int]           NULL,
  [event_rating]             [numeric](3, 2) NULL,
  [event_name]               [nvarchar](70)  NULL,
  [event_description]        [nvarchar](500) NULL,
  [image_path]               [nvarchar](150) NULL,
  [price]                    [numeric](8, 2) NULL,
  [alternative_address]      [nvarchar](max) NULL,
  CONSTRAINT [PK_Events] PRIMARY KEY CLUSTERED
    (
      [id_event] ASC
    )
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
) ON [PRIMARY]

CREATE TABLE [dbo].[Reservation_place] (
  [id_reservation] [int]  NOT NULL,
  [id_place]       [int]  NULL,
  [id_client]      [int]  NULL,
  [reservation_date] [date] NULL,
  [created]        [date] NULL,
  [modified]       [date] NULL,
  [deleted]    [date] NULL,
  CONSTRAINT [PK_Reservation_place] PRIMARY KEY CLUSTERED
    (
      [id_reservation] ASC
    )
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
) ON [PRIMARY]

CREATE TABLE [dbo].[Reservation_event] (
  [id_reservation] [int]  NOT NULL,
  [id_event]   [int]  NULL,
  [id_client]  [int]  NULL,
  [reservation_date] [date] NULL,
  [created]    [date] NULL,
  [modified]   [date] NULL,
  [deleted]    [date] NULL,
  CONSTRAINT [PK_Reservation_event] PRIMARY KEY CLUSTERED
    (
      [id_reservation] ASC
    )
    WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
    ON [PRIMARY]
) ON [PRIMARY]

ALTER TABLE [dbo].[Client]
  WITH CHECK ADD CONSTRAINT [FK_Clients_Roles] FOREIGN KEY ([id_role])
REFERENCES [dbo].[Role] ([id_role])

ALTER TABLE [dbo].[Client]
  CHECK CONSTRAINT [FK_Clients_Roles]

ALTER TABLE [dbo].[Place]
  WITH CHECK ADD CONSTRAINT [FK_Places_Clients] FOREIGN KEY ([id_client])
REFERENCES [dbo].[Client] ([id_client])

ALTER TABLE [dbo].[Place]
  CHECK CONSTRAINT [FK_Places_Clients]

ALTER TABLE [dbo].[Event]
  WITH CHECK ADD CONSTRAINT [FK_Events_Places] FOREIGN KEY ([id_place])
REFERENCES [dbo].[Place] ([id_place])

ALTER TABLE [dbo].[Event]
  CHECK CONSTRAINT [FK_Events_Places]

ALTER TABLE [dbo].[Reservation_place]
  WITH CHECK ADD CONSTRAINT [FK_Reservation_place_Clients] FOREIGN KEY ([id_client])
REFERENCES [dbo].[Client] ([id_client])

ALTER TABLE [dbo].[Reservation_place]
  CHECK CONSTRAINT [FK_Reservation_place_Clients]

ALTER TABLE [dbo].[Reservation_place]
  WITH CHECK ADD CONSTRAINT [FK_Reservation_place_Places] FOREIGN KEY ([id_place])
REFERENCES [dbo].[Place] ([id_place])

ALTER TABLE [dbo].[Reservation_place]
  CHECK CONSTRAINT [FK_Reservation_place_Places]

ALTER TABLE [dbo].[Reservation_event]
  WITH CHECK ADD CONSTRAINT [FK_Reservation_event_Client] FOREIGN KEY ([id_client])
REFERENCES [dbo].[Client] ([id_client])

ALTER TABLE [dbo].[Reservation_event]
  CHECK CONSTRAINT [FK_Reservation_event_Client]

ALTER TABLE [dbo].[Reservation_event]
  WITH CHECK ADD CONSTRAINT [FK_Reservation_event_Event] FOREIGN KEY ([id_event])
REFERENCES [dbo].[Event] ([id_event])

ALTER TABLE [dbo].[Reservation_event]
  CHECK CONSTRAINT [FK_Reservation_event_Event]
