// See https://aka.ms/new-console-template for more information

using cli.Commands.Login;
using cli.Commands.Me;
using cli.Commands.Register;
using Spectre.Console.Cli;

namespace cli;

/// <summary>
/// The entry point of the command line interface application.
/// </summary>
public static class Program
{
    /// <summary>
    /// The entry point of the command line interface application.
    /// </summary>
    /// <param name="args">The command line arguments.</param>
    /// <returns>The exit code of the application.</returns>
    public static int Main(string[] args)
    {
        var app = new CommandApp();
        
        app.Configure(config =>
        {
            config.SetApplicationName("livius");
            config.SetApplicationVersion("1.0.0.0");
            
            config.AddCommand<MeCommand>("me")
                .WithDescription("");
            
            config.AddCommand<LoginCommand>("login")
                .WithAlias("l")
                .WithDescription("");
            
            config.AddCommand<RegisterCommand>("register")
                .WithAlias("r")
                .WithDescription("");
        });

        return app.Run(args);
    }
}